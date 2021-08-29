package com.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    // 队列名 交换机名 路由名
    public static final String FANOUT_QUEUE_NAME = "test_fanout_queue";
    public static final String FANOUT_QUEUE_NAME1 = "test_fanout_queue1";
    public static final String TEST_FANOUT_EXCHANGE = "testFanoutExchange";

    public static final String DIRECT_QUEUE_NAME = "test_direct_queue";
    public static final String TEST_DIRECT_EXCHANGE = "testDirectExchange";
    public static final String DIRECT_ROUTING_KEY = "test";

    public static final String TOPIC_QUEUE_NAME = "test_topic_queue";
    public static final String TEST_TOPIC_EXCHANGE = "testTopicExchange";
    public static final String TOPIC_ROUTING_KEY = "test.*";

    // Fanout
    // 创建队列
    @Bean
    public Queue createFanoutQueue() {
        return new Queue(FANOUT_QUEUE_NAME);
    }

    // 创建队列
    @Bean
    public Queue createFanoutQueue1() {
        return new Queue(FANOUT_QUEUE_NAME1);
    }

    // 创建交换机
    @Bean
    public FanoutExchange defFanoutExchange() {
        return new FanoutExchange(TEST_FANOUT_EXCHANGE);
    }

    // 队列与交换机进行绑定，没有路由名
    @Bean
    Binding bindingFanout() {
        return BindingBuilder.bind(createFanoutQueue()).
                to(defFanoutExchange());
    }

    // 队列与交换机进行绑定，没有路由名
    @Bean
    Binding bindingFanout1() {
        return BindingBuilder.bind(createFanoutQueue1()).
                to(defFanoutExchange());
    }

    // Direct
    // 创建队列
    @Bean
    public Queue createDirectQueue() {
        return new Queue(DIRECT_QUEUE_NAME);
    }

    // 创建交换机
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(TEST_DIRECT_EXCHANGE);
    }

    // 队列与交换机与路由名进行绑定
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(createDirectQueue()).
                to(directExchange()).
                with(DIRECT_ROUTING_KEY);
    }

    // Topic
    // 创建队列
    @Bean
    public Queue createTopicQueue() {
        return new Queue(TOPIC_QUEUE_NAME);
    }

    // 创建交换机
    @Bean
    TopicExchange defTopicExchange() {
        return new TopicExchange(TEST_TOPIC_EXCHANGE);
    }

    // 对列与交换机与路由名绑定
    @Bean
    Binding bindingTopic() {
        return BindingBuilder.bind(createTopicQueue()).
                to(defTopicExchange()).
                with(TOPIC_ROUTING_KEY);
    }
}
