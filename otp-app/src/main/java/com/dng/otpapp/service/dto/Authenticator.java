package com.dng.otpapp.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Authenticator {
    private String authCode;
    private String sid;
}
