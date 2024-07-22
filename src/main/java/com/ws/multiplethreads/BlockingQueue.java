package com.ws.multiplethreads;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 阻塞队列示例
 *
 * @author wangsen
 * @date 2024/07/21
 * @description 阻塞队列示例
 */
public class BlockingQueue {

    public static void main(String[] args) {

        // 阻塞队列
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        // 生产者线程
        Thread producerOne = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    queue.put(i);
                    System.out.println("生产数据：" + i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        Thread producerTwo = new Thread(() -> {
            for (int i = 5; i < 10; i++) {
                try {
                    queue.put(i);
                    System.out.println("生产数据：" + i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 消费者线程
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Integer take = queue.take();
                    System.out.println("消费数据：" + take);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producerOne.start();
        producerTwo.start();

        // 等待生产者线程执行完毕
        // try {
        //     producerOne.join();
        //     producerTwo.join();
        // } catch (InterruptedException e) {
        //     Thread.currentThread().interrupt();
        // }

        // 消费队列线程
        consumer.start();


    }
}