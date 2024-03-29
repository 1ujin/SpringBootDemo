// package com.component.rabbitmq;
//
// import com.config.RabbitConfig;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
//
// @Component
// public class Producer {
//     @Autowired
//     private RabbitTemplate rabbitTemplate;
//
//     public void send2FanoutTestQueue(String massage) {
//         rabbitTemplate.convertAndSend(RabbitConfig.TEST_FANOUT_EXCHANGE, "", massage);
//     }
//
//     public void send2DirectTestQueue(String massage) {
//         rabbitTemplate.convertAndSend(RabbitConfig.TEST_DIRECT_EXCHANGE, RabbitConfig.DIRECT_ROUTING_KEY, massage);
//     }
//
//     public void send2TopicTestAQueue(String massage) {
//         rabbitTemplate.convertAndSend(RabbitConfig.TEST_TOPIC_EXCHANGE, "test.aaa", massage);
//     }
//
//     public void send2TopicTestBQueue(String massage) {
//         rabbitTemplate.convertAndSend(RabbitConfig.TEST_TOPIC_EXCHANGE, "test.bbb", massage);
//     }
// }
