package com.dng.otpapp.service;

import com.dng.otpapp.service.dto.Authenticator;
import com.dng.otpapp.service.dto.Delivery;
import com.dng.otpapp.service.dto.Requestor;
import com.dng.otpapp.service.dto.request.ClientRequest;
import core.OtpKey;
import org.springframework.stereotype.Service;
import org.springframework.util.AlternativeJdkIdGenerator;

@Service
public class AppService {

    private final AlternativeJdkIdGenerator idGenerator = new AlternativeJdkIdGenerator();

    public OtpKey genKeyFromReq(Requestor requestor){
        return new OtpKey(requestor.getTransId(),
                          requestor.getUserId(),
                          requestor.getService(),
                          requestor.getTransType(),
                          idGenerator.generateId().toString());
    }

    public OtpKey genKeyFromReq(Requestor requestor, Authenticator authenticator){
        return new OtpKey(requestor.getTransId(),
                requestor.getUserId(),
                requestor.getService(),
                requestor.getTransType(),
                authenticator.getSid());
    }

    public ClientRequest makeCliReq(Requestor requestor, Delivery delivery, String authCode, String sid){
        Authenticator authenticator = new Authenticator();
        authenticator.setAuthCode(authCode);
        authenticator.setSid(sid);
        return new ClientRequest(requestor, delivery, authenticator);
    }
}
