package utils.test;

import com.google.common.base.Ticker;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class TestTicker extends Ticker {
    private final AtomicLong nanos = new AtomicLong();

    /** Advances the ticker value by {@code time} in {@code timeUnit}. */
    public TestTicker advance(long time, TimeUnit timeUnit) {
        nanos.addAndGet(timeUnit.toNanos(time));
        return this;
    }

    @Override
    public long read() {
        long value = nanos.getAndAdd(0);
        System.out.println("is called " + value);
        return value;
    }
}
