package core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @Getter @Setter @ToString
public class OtpAuthCode {
    String passCode;
    Integer nTries;

    public OtpAuthCode decreaseTries(){
        this.nTries -= 1;
        return this;
    }

    public OtpAuthCode(String passCode) {
        this.passCode = passCode;
    }
}
