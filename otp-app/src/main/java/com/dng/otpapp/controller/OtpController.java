package com.dng.otpapp.controller;

import com.dng.otpapp.service.AppService;
import com.dng.otpapp.service.ClientService;
import com.dng.otpapp.service.dto.request.ClientRequest;
import com.dng.otpapp.service.dto.request.CreateRequest;
import com.dng.otpapp.service.dto.request.ValidateRequest;
import com.dng.otpapp.service.dto.response.ClientResponse;
import com.dng.otpapp.service.dto.response.OtpResponse;
import comm.impl.OtpRes;
import core.OtpAuthCode;
import core.OtpCacheManager;
import core.OtpKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.impl.OtpService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("otp")
@Slf4j
public class OtpController {

    private final OtpService otpService;
    private final AppService appService;
    private final ClientService clientService;

    public OtpController(OtpService otpService,
                         AppService appService,
                         ClientService clientService) {
        this.otpService = otpService;
        this.appService = appService;
        this.clientService = clientService;
    }

    @PostMapping("create")
    @SuppressWarnings("ConstantConditions")
    public ResponseEntity<Object> createOtp(@RequestBody CreateRequest request){
        log.info("Create Request Receive [" + request.toString() + "]");
        OtpKey key = appService.genKeyFromReq(request.getRequestor());
        String sid = key.getSid();
        OtpAuthCode authCode = otpService.create(key)
                                         .getReturnObject();

        log.info("OTP key [" + key.toString() + "]");

        ClientRequest cliReq = appService.makeCliReq(request.getRequestor(),
                                                    request.getDelivery(),
                                                    authCode.getPassCode(), sid);

        CompletableFuture<ResponseEntity<ClientResponse>> smsReq = clientService.send(cliReq);

        try {
            ResponseEntity<ClientResponse> res = smsReq.get(10, TimeUnit.SECONDS);
            if (res.getStatusCode().equals(HttpStatus.OK)){
                return ResponseEntity.ok(OtpResponse.successCreated(sid));
            } else {
                return ResponseEntity.internalServerError()
                                     .body(OtpResponse.failCreated(res.getBody().getMessage()));
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            return ResponseEntity.internalServerError()
                                 .body(OtpResponse.failCreated(e.getCause().getMessage()));
        }
    }

    @PostMapping("validate")
    public ResponseEntity<Object> verifyOtp(@RequestBody ValidateRequest request){
        log.info("Validate Request Receive [" + request.toString() + "]");

        OtpKey key = appService.genKeyFromReq(request.getRequestor(), request.getAuthenticator());
        OtpAuthCode code = new OtpAuthCode(request.getAuthenticator().getAuthCode());
        OtpRes<OtpAuthCode> otpRes = otpService.verify(key, code);

        log.info("Validate Key [" + key.toString() + "]");
        log.info("AuthCode [" + code.toString() + "]");
        log.info("OTP Response [" + otpRes.toString() + "]");

        switch (otpRes.getRc()) {
            case "OK":
                return ResponseEntity.ok(OtpResponse.successVerify());
            case "WRONG_OTP":
                return ResponseEntity.ok(OtpResponse.wrongOTP(otpRes));
            case "NO_OTP":
                return ResponseEntity.ok(OtpResponse.noOTP(otpRes));
            case "INVALID":
                return ResponseEntity.ok(OtpResponse.invalidOTP(otpRes));
            default:
                return ResponseEntity.ok(OtpResponse.unknown());
        }
    }

}
