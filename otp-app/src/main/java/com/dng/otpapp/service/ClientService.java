package com.dng.otpapp.service;

import com.dng.otpapp.service.dto.request.ClientRequest;
import com.dng.otpapp.service.dto.response.ClientResponse;
import config.LoadConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.naming.ConfigurationException;
import java.lang.module.Configuration;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ClientService {
    private final RestTemplate restTemplate;
    private final String smsURL;

    public ClientService() {
        smsURL = LoadConfig.getProperties("client-url");
        if (smsURL==null){
            try {
                throw new ConfigurationException("client-url not found in config.properties");
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        }
        this.restTemplate = new RestTemplateBuilder().rootUri(smsURL)
                                                    .build();
    }

    @Async
    public CompletableFuture<ResponseEntity<ClientResponse>> send(ClientRequest request){
        ResponseEntity<ClientResponse>  response = restTemplate.postForEntity(smsURL, request, ClientResponse.class);
        log.info(response.toString());
        return CompletableFuture.completedFuture(response);
    }
}
