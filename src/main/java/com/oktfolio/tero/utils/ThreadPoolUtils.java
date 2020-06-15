package com.oktfolio.tero.utils;

import ch.qos.logback.core.util.TimeUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/13
 */
public class ThreadPoolUtils {
    private static ThreadFactory NAMED_THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat
            ("guava-thread-pool-%d").build();

    private static ExecutorService executorService;

    public static ExecutorService getExecutorService() {
        int corePoolSize = 5;
        int maximumPoolSize = 20;
        long keepAliveTime = 500L;
        int capacity = Integer.MAX_VALUE;
        return getExecutorService(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                capacity);
    }

    public static ExecutorService getExecutorService(int corePoolSize,
                                                     int maximumPoolSize,
                                                     long keepAliveTime,
                                                     int capacity) {
        if (executorService == null || executorService.isShutdown()) {
            executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                    TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(capacity), NAMED_THREAD_FACTORY,
                    new ThreadPoolExecutor.AbortPolicy());
        }
        return executorService;
    }
}
