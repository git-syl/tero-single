package com.oktfolio.tero.lock;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/17
 */
public class AbstractDistributedLock implements DistributedLock{
    @Override
    public DLockInfo tryLock() {
        return null;
    }

    @Override
    public DLockInfo lock() {
        return null;
    }

    @Override
    public void unlock() {

    }

    @Override
    public boolean isLocked() {
        return false;
    }
}
