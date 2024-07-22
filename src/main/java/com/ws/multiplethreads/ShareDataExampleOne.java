package com.ws.multiplethreads;

/**
 * 线程间共享数据
 * <p>
 * 共享数据示例: 使用共享变量
 *
 * @author wangsen_a
 * @date 2024/07/19
 */
public class ShareDataExampleOne {

    private volatile int shareData = 0;

    /**
     * 读
     *
     * @return int
     */
    private int read() {
        return shareData;
    }

    /**
     * 写
     *
     * @param shareData 共享数据
     */
    private void write(int shareData) {
        this.shareData = shareData;
    }

    public static void main(String[] args) {
        ShareDataExampleOne exampleOne = new ShareDataExampleOne();

        Thread write = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                exampleOne.write(i);
                System.out.println("write: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread read = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                int data = exampleOne.read();
                System.out.println("read: " + data);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        write.start();
        read.start();

    }
}
