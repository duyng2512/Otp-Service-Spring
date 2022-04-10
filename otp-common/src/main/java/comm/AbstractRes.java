package comm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor @Getter
public abstract class AbstractRes <T>{
    protected boolean success;
    protected String message;
    protected String rc;
    protected LocalDateTime timeStamp = LocalDateTime.now();
    protected T returnObject;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractRes<?> that = (AbstractRes<?>) o;
        return success == that.success && message.equals(that.message) && rc.equals(that.rc) && returnObject.equals(that.returnObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, message, rc, returnObject);
    }
}
