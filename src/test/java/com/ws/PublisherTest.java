package com.ws;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 推送消息测试
 *
 * @author wangsen
 * @date 2024/12/01
 */
@SpringBootTest
public class PublisherTest {

    private RabbitTemplate rabbitTemplate;

    @Test
    void testSimpleQueue() {


        rabbitTemplate.convertAndSend("simple.queueOne", "world");

    }


}
