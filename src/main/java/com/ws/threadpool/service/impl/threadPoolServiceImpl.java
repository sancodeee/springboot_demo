package com.ws.threadpool.service.impl;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池服务实现
 *
 * @author wangsen
 * @date 2024/06/30
 */
@Service
public class threadPoolServiceImpl {

    public static void main(String[] args) {
        // 核心线程数
        int corePoolSize = 5;
        // 最大线程数
        int maximumPoolSize = 10;
        // 空闲线程存活时间
        long keepAliveTime = 60L;
        // 空闲线程存活时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        // 任务等待队列
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(100);

        // 自定义线程工厂
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger count = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "customerThread-" + count.getAndIncrement());
            }
        };

        // 拒绝策略
        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();

        ExecutorService executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, callerRunsPolicy);

    }

}
