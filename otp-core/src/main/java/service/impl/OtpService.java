package service.impl;

import comm.impl.OtpRes;
import core.OtpAuthCode;
import core.OtpCacheManager;
import core.OtpKey;
import org.springframework.stereotype.Service;
import service.IOtpService;

import java.util.concurrent.ExecutionException;

@Service
public class OtpService implements IOtpService {

    private final OtpGenerator otpGenerator;
    private final OtpCacheManager otpCacheManager;

    public OtpService(OtpGenerator otpGenerator, OtpCacheManager otpCacheManager) {
        this.otpGenerator = otpGenerator;
        this.otpCacheManager = otpCacheManager;
    }

    @Override
    public OtpRes<OtpAuthCode> create(OtpKey key) {
        OtpAuthCode authCode = new OtpAuthCode(otpGenerator.getOtp(), 3);
        otpCacheManager.getCache().put(key, authCode);
        return OtpRes.successCreate(authCode);
    }

    @Override
    public OtpRes<OtpAuthCode> verify(OtpKey key, OtpAuthCode authCode) {
        OtpAuthCode cacheCode;
        try {
            cacheCode = otpCacheManager.getCache().get(key);
        } catch (ExecutionException exception) {
            return OtpRes.noOTP(authCode);
        }

        // Compare OTP
        if (cacheCode.getPassCode().equals(authCode.getPassCode())){
            otpCacheManager.getCache().refresh(key);
            return OtpRes.successVerify(authCode);
        } else {
            if (cacheCode.getNTries().equals(0)){
                return OtpRes.invalidOTP(authCode);
            }
            otpCacheManager.getCache().put(key, cacheCode.decreaseTries());
            authCode.setNTries(cacheCode.getNTries());
            return OtpRes.wrongOTP(authCode);
        }
    }

    @Override
    public OtpRes<OtpAuthCode> refresh(OtpKey key) {
        OtpAuthCode authCode = new OtpAuthCode(otpGenerator.getOtp(), 3);
        otpCacheManager.getCache().put(key, authCode);
        return OtpRes.successRefresh(authCode);
    }
}
