package service;

import comm.impl.InternalRes;
import comm.impl.OtpRes;
import core.OtpAuthCode;
import core.OtpKey;

public interface IOtpService {
    OtpRes<OtpAuthCode> create(OtpKey key);
    OtpRes<OtpAuthCode> verify(OtpKey key, OtpAuthCode authCode);
    OtpRes<OtpAuthCode> refresh(OtpKey key);
}
