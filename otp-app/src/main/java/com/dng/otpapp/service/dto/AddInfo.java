package com.dng.otpapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class AddInfo {
    private String message;
    private String sid;
    private Integer nTries;
}
