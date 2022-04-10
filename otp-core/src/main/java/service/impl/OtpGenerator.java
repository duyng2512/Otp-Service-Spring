package service.impl;

import config.LoadConfig;
import org.springframework.stereotype.Service;
import service.IOtpGenerator;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


@Service
public class OtpGenerator implements IOtpGenerator {
    final String allowStr;
    final int otpLen;
    final byte[] allowBytes;
    final SecureRandom random;

    public OtpGenerator() {
        allowStr = LoadConfig.getProperties("allow-characters");
        otpLen = Integer.parseInt(LoadConfig.getProperties("otp-max-length"));
        allowBytes = allowStr.getBytes();

        SecureRandom tempSecureRandom;
        try { tempSecureRandom = SecureRandom.getInstance("SHA1PRNG");}
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            tempSecureRandom = new SecureRandom();
        }
        random = tempSecureRandom;
    }


    public String getOtp() {
        byte[] bytes = new byte[otpLen];
        random.nextBytes(bytes);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < otpLen; i++) {
            builder.append((char) allowBytes[ (bytes[i] & 0xF) % allowStr.length()]);
        }
        return builder.toString();
    }
}
