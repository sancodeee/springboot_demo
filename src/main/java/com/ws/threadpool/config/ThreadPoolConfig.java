package com.ws.threadpool.config;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * 线程池配置
 *
 * @author wangsen
 * @date 2024/08/05
 */
@Slf4j
@Configuration
public class ThreadPoolConfig {

    private ThreadPoolTaskExecutor executor;
    private ScheduledExecutorService monitor;

    @Bean(name = "customThreadPool")
    public Executor threadPoolTaskExecutor() {
        executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        // 阻塞队列容量,默认使用：LinkedBlockingQueue
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("CustomPool-");
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 等待任务完成时间(秒)
        executor.setAwaitTerminationSeconds(30);
        // 拒绝策略，当任务超过队列大小时，使用CallerRunsPolicy策略，即直接在调用者线程中运行任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();


        // 线程池监控配置
        monitor = Executors.newSingleThreadScheduledExecutor();
        monitor.scheduleAtFixedRate(() -> {
            log.info("""
                            \n线程池监控信息：\s
                            当前线程数:{} \s
                            活动线程数:{} \s
                            已完成任务数:{} \s
                            全部任务数:{}
                            """,
                    executor.getPoolSize(),
                    executor.getActiveCount(),
                    executor.getThreadPoolExecutor().getCompletedTaskCount(),
                    executor.getThreadPoolExecutor().getTaskCount());
        }, 0, 5, TimeUnit.MINUTES);

        return executor;
    }

    /**
     * 关闭线程池资源
     * <p>
     * 在 Spring 容器关闭或 Bean 被销毁之前，执行该方法
     */
    @PreDestroy
    public void shutdownThreadPool() {
        if (monitor != null && !monitor.isShutdown()) {
            monitor.shutdown();
            try {
                if (!monitor.awaitTermination(30, TimeUnit.SECONDS)) {
                    // 强制关闭
                    monitor.shutdownNow();
                }
            } catch (InterruptedException e) {
                monitor.shutdownNow();
                Thread.currentThread().interrupt();
            }

        }

        if (executor != null) {
            executor.shutdown();
            try {
                if (!executor.getThreadPoolExecutor().awaitTermination(30, TimeUnit.SECONDS)) {
                    // 强制关闭
                    executor.getThreadPoolExecutor().shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.getThreadPoolExecutor().shutdownNow();
                // 重新设置中断标志
                Thread.currentThread().interrupt();
            }
        }
    }


}
