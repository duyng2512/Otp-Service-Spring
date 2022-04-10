package comm.impl;

import comm.AbstractRes;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class OtpRes<T> extends AbstractRes<T> {

    public OtpRes(boolean success, String message, String rc, LocalDateTime timeStamp, T returnObject) {
        super(success, message, rc, timeStamp, returnObject);
    }

    public static <T> OtpRes<T> successCreate(T authCode){
        return new OtpRes<>(true, "Otp create successfully",
                            "OK", LocalDateTime.now(), authCode);
    }

    public static <T> OtpRes<T> successRefresh(T authCode){
        return new OtpRes<>(true, "Otp create refresh",
                "OK", LocalDateTime.now(), authCode);
    }

    public static <T> OtpRes<T> successVerify(T authCode){
        return new OtpRes<>(true, "Otp verify successfully",
                "OK", LocalDateTime.now(), authCode);
    }

    public static <T> OtpRes<T> wrongOTP(T authCode){
        return new OtpRes<>(false, "Wrong OTP",
                            "WRONG_OTP", LocalDateTime.now(), authCode);
    }

    public static <T> OtpRes<T> noOTP(T authCode){
        return new OtpRes<>(false, "no OTP found or expire",
                        "NO_OTP", LocalDateTime.now(), authCode);
    }

    public static <T> OtpRes<T> invalidOTP(T authCode){
        return new OtpRes<>(false, "To many tries",
                "INVALID", LocalDateTime.now(), authCode);
    }

}
