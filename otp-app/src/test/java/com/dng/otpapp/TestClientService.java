package com.dng.otpapp;

import com.dng.otpapp.config.AppConfig;
import com.dng.otpapp.service.ClientService;
import com.dng.otpapp.service.dto.request.ClientRequest;
import com.dng.otpapp.service.dto.response.ClientResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.concurrent.Executor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@SpringBootTest(classes = {ClientService.class})
public class TestClientService {

    @Autowired
    ClientService clientService;

    @Test
    public void testCallSMS(){
        ClientRequest request = new ClientRequest(null, null, null);
        clientService.send(request);
    }
}