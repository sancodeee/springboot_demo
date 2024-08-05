package com.ws.multiplethreads;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 创建线程演示
 *
 * @author wangsen
 * @date 2024/08/05
 */
@Slf4j
public class CreatThreadTest {

    @Test
    public void testOne(){

        // 通过实现Runnable实现创建一个线程
        Thread thread = new Thread(() -> {
            log.info("hello thread");
        });
        thread.start();


    }

}
