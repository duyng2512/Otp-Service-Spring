package core;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import config.LoadConfig;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service
public class OtpCacheManager {

    Logger logger = Logger.getLogger(OtpCacheManager.class.getSimpleName());

    private Ticker ticker;

    private final LoadingCache<OtpKey, OtpAuthCode> loadingCache;

    public OtpCacheManager() {
        RemovalListener<OtpKey, OtpAuthCode> removalListener = removalNotification -> {
            System.out.println(removalNotification.getCause());
            System.out.println(removalNotification.wasEvicted());
        };

        CacheLoader<OtpKey, OtpAuthCode> cacheLoader = new CacheLoader<>() {
            @Override
            public OtpAuthCode load(OtpKey otpKey) throws Exception {
                throw new ExecutionException("No AuthCode associate with key " + otpKey.toString(), new NoSuchElementException());
            }
        };

        ticker = Ticker.systemTicker();
        
        long expireTime = Long.parseLong(LoadConfig.getProperties("otp-expire-time"));
        logger.info("[otp-expire-time]" + expireTime);
        boolean statEnable = Boolean.parseBoolean(LoadConfig.getProperties("stat-enable"));
        logger.info("[stat-enable]" + statEnable);
        int concurrentLevel = Integer.parseInt(LoadConfig.getProperties("concurrent-level"));
        logger.info("[concurrentLevel]" + concurrentLevel);

        CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();

        // Soft value, evict LRU
        builder.softValues();

        // Expire time in milliseconds;
        builder.expireAfterWrite(expireTime, TimeUnit.SECONDS);

        // Enable Statistic
        if (statEnable) builder.recordStats();

        // Add Ticker
        builder.ticker(ticker);

        // Concurrency level
        builder.concurrencyLevel(concurrentLevel);

        // Capacity
        builder.initialCapacity(100_000);

        // Removal Listener
        loadingCache = builder.removalListener(removalListener).build(cacheLoader);
    }
    
    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    public LoadingCache<OtpKey, OtpAuthCode> getCache(){
        return this.loadingCache;
    };
}
