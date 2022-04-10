package com.dng.otpapp.service.dto.request;

import com.dng.otpapp.service.dto.Authenticator;
import com.dng.otpapp.service.dto.Requestor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ValidateRequest {
    private Requestor requestor;
    private Authenticator authenticator;
}
