package vip.vinyoung.cache.impl;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RLock;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import vip.vinyoung.cache.service.CacheHelper;
import java.time.Instant;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 工具类实现
 *
 * @author wangyunshu
 * @since 2024-03-24
 */
@Component
@Qualifier("cacheHelper")
public class RedisHelper implements CacheHelper {
    @Resource
    private RedissonClient redissonClient;

    public RedisHelper() {

    }

    public RedisHelper(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public RLock getLock(String key) {
        return redissonClient.getLock(key);
    }

    @Override
    public void unLock(RLock lock) {
        if (lock == null) {
            return;
        }
        if (lock.isLocked()) {
            lock.unlock();
        }
    }

    @Override
    public void put(String cacheName, String key, @NotNull Object value) {
        put(cacheName, key, value, DEFAULT_CACHE_CYCLE);
    }

    @Override
    public void put(String cacheName, String key, @NotNull Object value, long duration) {
        RBucket<Object> bucket = redissonClient.getBucket(generateCacheKey(cacheName, key));
        if (duration > 0) {
            bucket.set(value, duration, TimeUnit.SECONDS);
        } else {
            bucket.set(value);
        }
    }

    @Override
    public Object get(String cacheName, String key) {
        return get(cacheName, key, Object.class);
    }

    @Override
    public <T> T get(String cacheName, String key, @NotNull Class<T> tClass) {
        RBucket<T> bucket = redissonClient.getBucket(generateCacheKey(cacheName, key));
        return bucket.get();
    }

    @Override
    public void remove(String cacheName, String key) {
        RKeys keys = redissonClient.getKeys();
        keys.delete(generateCacheKey(cacheName, key));
    }

    @Override
    public void addSet(String cacheName, String key, Object value) {
        addSet(cacheName, key, value, 0);
    }

    @Override
    public void addSet(String cacheName, String key, Object value, long second) {
        RSet<Object> set = redissonClient.getSet(generateCacheKey(cacheName, key));
        set.add(value);
        if (second > 0) {
            set.expire(Instant.now().plusSeconds(second));
        }
    }

    @Override
    public Set<Object> getSet(String cacheName, String key) {
        return redissonClient.getSet(generateCacheKey(cacheName, key));
    }

    @Override
    public Object getSetRandomItem(String cacheName, String key) {
        Set<Object> set = getSet(cacheName, key);
        if (CollectionUtils.isEmpty(set)) {
            return null;
        }
        return ((RSet<Object>) set).random();
    }

    @Override
    public void deleteSetItem(String cacheName, String key, Object value) {
        RSet<Object> set = (RSet<Object>) getSet(cacheName, key);
        set.remove(value);
    }
}
