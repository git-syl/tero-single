package com.oktfolio.tero.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;

import java.util.concurrent.TimeUnit;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
public class ZookeeperDistributedLock implements DistributedLock {

    private CuratorFramework client;

    public ZookeeperDistributedLock(CuratorFramework client) {
        this.client = client;
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
    public void unlock() {

    }

    @Override
    public boolean isLocked() {
        return false;
    }

    /**
     * 可冲入 公平 互斥锁
     * @param path
     * @param time
     * @param unit
     * @return
     * @throws Exception
     */
    private DLockInfo sharedReentrantLock(String path, long time, TimeUnit unit) throws Exception {
        // String lockPath = ROOT_LOCK_NODE + SEPARATOR + path;
        // 获取底层ZooKeeper Client API
        // ZooKeeper zooKeeper = client.getZookeeperClient().getZooKeeper();
        // Shared Reentrant Lock
        InterProcessMutex lock = new InterProcessMutex(client, path);
        boolean isLocked = lock.acquire(time, unit);
        return new DLockInfo();
    }

    /**
     * 可重入 公平 读写锁
     * @param path
     * @param time
     * @param unit
     * @param readOnly
     * @return
     * @throws Exception
     */
    private DLockInfo sharedReentrantReadWriteLock(String path, long time, TimeUnit unit, boolean readOnly) throws Exception {
        // String lockPath = ROOT_LOCK_NODE + SEPARATOR + path;
        InterProcessReadWriteLock rwLock = new InterProcessReadWriteLock(client, path);
        InterProcessMutex lock = readOnly ? rwLock.readLock() : rwLock.writeLock();
        boolean isLocked = lock.acquire(time, unit);
        return new DLockInfo();
    }
}
