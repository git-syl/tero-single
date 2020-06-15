package com.oktfolio.tero.lock;


/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
public interface DistributedLock {

    // LockInfo tryLock();

    DLockInfo tryLock();

    DLockInfo lock();

    void unlock();

    boolean isLocked();
}
