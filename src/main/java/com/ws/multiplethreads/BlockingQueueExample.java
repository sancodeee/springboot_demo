package com.ws.multiplethreads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 线程间通信
 * <p>
 * 阻塞队列示例
 *
 * @author wangsen_a
 * @date 2024/07/19
 */
public class BlockingQueueExample {

    public static void main(String[] args) {

        // 阻塞队列
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

        // 生产者线程
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {

                    queue.put(i);
                    System.out.println("produced data: " + i);

                }
            } catch (InterruptedException e) {
                // 设置当前线程中断状态
                Thread.currentThread().interrupt();
            }
        });

        // 消费者线程：消费队列消息，实现线程间的数据传递
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    // 队列数据
                    Integer queueData = queue.take();
                    System.out.println("take data: " + queueData);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        try {
            // 保证生产者线程执行完毕后 再执行消费者线程
            producer.join();

        } catch (InterruptedException e) {
            // 设置当前线程中断状态
            Thread.currentThread().interrupt();
        }
        consumer.start();

    }


}
