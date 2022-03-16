// package com.component.rabbitmq;
//
// import com.config.RabbitConfig;
// import org.springframework.amqp.core.Message;
// import org.springframework.amqp.rabbit.annotation.*;
// import org.springframework.stereotype.Component;
//
// import java.nio.charset.StandardCharsets;
//
// @Component
// public class Consumer {
//     @RabbitListener(
//             bindings = {
//                     @QueueBinding(
//                             value = @Queue(value = RabbitConfig.FANOUT_QUEUE_NAME, durable = "true"),
//                             exchange = @Exchange(value = RabbitConfig.TEST_FANOUT_EXCHANGE, type = "fanout")
//                     )
//             })
//     @RabbitHandler()
//     public void processFanoutMsg(Message message) {
//         String msg = new String(message.getBody(), StandardCharsets.UTF_8);
//         System.out.println("received Fanout message : " + msg);
//     }
//
//     @RabbitListener(
//             bindings = {
//                     @QueueBinding(
//                             value = @Queue(value = RabbitConfig.FANOUT_QUEUE_NAME1, durable = "true"),
//                             exchange = @Exchange(value = RabbitConfig.TEST_FANOUT_EXCHANGE, type = "fanout")
//                     )
//             })
//     @RabbitHandler
//     public void processFanout1Msg(Message message) {
//         String msg = new String(message.getBody(), StandardCharsets.UTF_8);
//         System.out.println("received Fanout1 message : " + msg);
//     }
//
//     @RabbitListener(
//             bindings = {
//                     @QueueBinding(
//                             value = @Queue(value = RabbitConfig.DIRECT_QUEUE_NAME, durable = "true"),
//                             exchange = @Exchange(value = RabbitConfig.TEST_DIRECT_EXCHANGE),
//                             key = RabbitConfig.DIRECT_ROUTING_KEY
//                     )
//             })
//     @RabbitHandler
//     public void processDirectMsg(Message message) {
//         String msg = new String(message.getBody(), StandardCharsets.UTF_8);
//         System.out.println("received Direct message : " + msg);
//     }
//
//     @RabbitListener(
//             bindings = {
//                     @QueueBinding(
//                             value = @Queue(value = RabbitConfig.TOPIC_QUEUE_NAME, durable = "true"),
//                             exchange = @Exchange(value = RabbitConfig.TEST_TOPIC_EXCHANGE, type = "topic"),
//                             key = RabbitConfig.TOPIC_ROUTING_KEY
//                     )
//             })
//     @RabbitHandler
//     public void processTopicMsg(Message message) {
//         String msg = new String(message.getBody(), StandardCharsets.UTF_8);
//         System.out.println("received Topic message : " + msg);
//     }
// }
