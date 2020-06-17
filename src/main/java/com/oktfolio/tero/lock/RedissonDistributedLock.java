package com.oktfolio.tero.lock;

import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/17
 */
public class RedissonDistributedLock extends AbstractDistributedLock {

    private final RedissonClient redisson;
    private RLock lock;

    public RedissonDistributedLock(RedissonClient redisson) {
        super();
        this.redisson = redisson;
    }

    @Override
    public DLockInfo lock() {
        return super.lock();
    }

    @Override
    public void unlock() {
        super.unlock();
    }

    private RLock lock(String key, DLockTypeEnum type, boolean readOnly) {
        switch (type) {
            case FAIR:
                return redisson.getFairLock(key);
            case READ_WRITE:
                RReadWriteLock readWriteLock = redisson.getReadWriteLock(key);
                return readOnly ? readWriteLock.readLock() : readWriteLock.writeLock();
            default:
                return redisson.getLock(key);
        }
    }
}
