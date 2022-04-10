package com.dng.otpapp.service.dto.response;

import com.dng.otpapp.service.dto.AddInfo;
import comm.impl.OtpRes;
import core.OtpAuthCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
public class OtpResponse {
    private Instant timeStamp;
    private String rc;
    private AddInfo addInfo;

    static public OtpResponse successCreated(String sid) {
        OtpResponse res =  new OtpResponse();
        AddInfo addInfo = new AddInfo("OTP sent to client", sid, 3);
        res.setAddInfo(addInfo);
        res.setRc("00");
        res.setTimeStamp(Instant.now());
        return res;
    }

    static public OtpResponse failCreated(String message) {
        OtpResponse res =  new OtpResponse();
        AddInfo addInfo = new AddInfo(message, null, null);
        res.setAddInfo(addInfo);
        res.setRc("01");
        res.setTimeStamp(Instant.now());
        return res;
    }

    static public OtpResponse successVerify() {
        OtpResponse res =  new OtpResponse();
        AddInfo addInfo = new AddInfo("OTP correct", null, null);
        res.setAddInfo(addInfo);
        res.setRc("00");
        res.setTimeStamp(Instant.now());
        return res;
    }

    static public OtpResponse wrongOTP(OtpRes<OtpAuthCode> otpRes) {
        OtpResponse res =  new OtpResponse();
        AddInfo addInfo = new AddInfo(otpRes.getMessage(), null, otpRes.getReturnObject().getNTries());
        res.setAddInfo(addInfo);
        res.setRc("01");
        res.setTimeStamp(Instant.now());
        return res;
    }

    static public OtpResponse noOTP(OtpRes<OtpAuthCode> otpRes) {
        OtpResponse res =  new OtpResponse();
        AddInfo addInfo = new AddInfo(otpRes.getMessage(), null, null);
        res.setAddInfo(addInfo);
        res.setRc("03");
        res.setTimeStamp(Instant.now());
        return res;
    }

    static public OtpResponse invalidOTP(OtpRes<OtpAuthCode> otpRes) {
        OtpResponse res =  new OtpResponse();
        AddInfo addInfo = new AddInfo(otpRes.getMessage(), null, null);
        res.setAddInfo(addInfo);
        res.setRc("04");
        res.setTimeStamp(Instant.now());
        return res;
    }

    static public OtpResponse unknown() {
        OtpResponse res =  new OtpResponse();
        AddInfo addInfo = new AddInfo("Unknown Err", null, null);
        res.setAddInfo(addInfo);
        res.setRc("05");
        res.setTimeStamp(Instant.now());
        return res;
    }
}
