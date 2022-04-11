package com.dng.otpapp;

import com.dng.otpapp.service.AppService;
import com.dng.otpapp.service.dto.Requestor;
import core.OtpKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import utils.log.AppLog;

import java.time.Instant;

public class TestAdhoc {

    @Test
    public void testGenKey (){
        Requestor requestor = new Requestor();
        requestor.setService("SERVICE");
        requestor.setTimeStamp(Instant.now());
        requestor.setTransId("1009991");
        requestor.setUserId("100001");
        requestor.setTransType("ECOM");

        OtpKey key = new AppService().genKeyFromReq(requestor);
        System.out.println(key.toString());
    }

    @Test
    public void testLog(){
        AppLog.cache().info("Hello World");
        AppLog.messages().info("Hello World");
    }

}
