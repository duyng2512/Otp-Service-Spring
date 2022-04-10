package com.dng.otpapp.service.dto.request;

import com.dng.otpapp.service.dto.Authenticator;
import com.dng.otpapp.service.dto.Delivery;
import com.dng.otpapp.service.dto.Requestor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ClientRequest {
    private final Requestor requestor;
    private final Delivery delivery;
    private final Authenticator authenticator;
}
