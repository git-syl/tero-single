package com.oktfolio.tero.lock;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
public class RedisDistributedLock extends AbstractDistributedLock {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisDistributedLock(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void unlock() {
    }

    @Override
    public DLockInfo tryLock() {
        return null;
    }

    @Override
    public DLockInfo lock() {
        return null;
    }

    @Override
    public boolean isLocked() {
        return false;
    }
}
