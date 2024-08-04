package com.ws.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * rabbitMQ 配置
 *
 * @author wangsen
 * @date 2024/07/28
 */
@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "FirstQueue";
    public static final String EXCHANGE_NAME = "BookExchange";

    @Bean
    public Queue firstQueue() {
        // 参数durable：是否持久化
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding firstQueuebinding(Queue firstQueue, TopicExchange topicExchange) {
        // bindingKey 为 Book.#
        return BindingBuilder.bind(firstQueue).to(topicExchange).with("Book.#");
    }


}
