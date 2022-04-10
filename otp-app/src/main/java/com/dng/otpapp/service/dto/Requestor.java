package com.dng.otpapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter @Setter @ToString
public class Requestor {
    private String service;
    private String userId;
    private Instant timeStamp;
    private String transId;
    private String transType;
}