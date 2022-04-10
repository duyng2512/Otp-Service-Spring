package com.dng.otpapp.service.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResponse {
    private String timeStamp;
    private String rc;
    private String message;
}
