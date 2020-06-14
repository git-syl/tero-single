package com.oktfolio.tero.utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/13
 */
public class ThreadPoolUtils {

    private ThreadPoolExecutor pool = null;

    public ThreadPoolExecutor getNewInstance(int corePoolSize, int maxThreadSize, String poolName, int maxTaskSize) {

        pool = createPool(corePoolSize, maxThreadSize, poolName, maxTaskSize);

        if (pool == null) {
            throw new NullPointerException();
        } else {
            return pool;
        }
    }

    private ThreadPoolExecutor createPool(int corePoolSize, int maxThreadSize, String poolName, int maxTaskSize) {

        return new ThreadPoolExecutor(corePoolSize, maxThreadSize, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(maxTaskSize),
                new CustomThreadFactory(poolName),
                new RejectedExecutionHandlerImpl());
    }

    private class CustomThreadFactory implements ThreadFactory {

        private final String poolName;

        public CustomThreadFactory(String poolName) {
            this.poolName = poolName;
        }

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String nowThreadName = "";
            nowThreadName = poolName + count.addAndGet(1);
            t.setName(nowThreadName);
            return t;
        }
    }

    private class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                System.out.println("重回队列");
                executor.getQueue().put(r);
            } catch (Exception e) {

            }
        }
    }

}
