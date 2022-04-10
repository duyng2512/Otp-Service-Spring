package com.dng.otpapp.service.dto.request;

import com.dng.otpapp.service.dto.Delivery;
import com.dng.otpapp.service.dto.Requestor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CreateRequest {
    private Requestor requestor;
    private Delivery delivery;
}
