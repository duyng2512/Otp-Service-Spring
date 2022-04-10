package core;

import lombok.*;

@Getter
@ToString @EqualsAndHashCode
public class OtpKey {
    private final String transId;
    private final String userId;
    private final String service;
    private final String transType;
    private final String sid;

    public OtpKey(String transId, String userId,
                  String service, String transType,
                  String sid
    ) {
        this.transId = transId;
        this.userId = userId;
        this.service = service;
        this.transType = transType;
        this.sid = sid;
    }
}
