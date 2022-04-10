package comm.impl;


import comm.AbstractRes;
import lombok.*;

import java.time.LocalDateTime;


@ToString
@Getter @Setter
public class InternalRes<T> extends AbstractRes<T> {

    @Builder
    public InternalRes(boolean success, String message, String rc, LocalDateTime timeStamp, T returnObject) {
        super(success, message, rc, timeStamp, returnObject);
    }
}
