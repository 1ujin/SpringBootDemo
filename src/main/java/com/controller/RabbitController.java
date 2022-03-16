// package com.controller;
//
// import com.component.rabbitmq.Producer;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
//
// @Controller
// @RequestMapping("/rabbit")
// public class RabbitController {
//     @Autowired
//     private Producer producer;
//
//     @GetMapping(value = "/sendFanout")
//     @ResponseBody
//     @Transactional(rollbackFor = Exception.class)
//     public void sendMsg(){
//         producer.send2FanoutTestQueue("this is a test fanout message!");
//     }
//
//     @GetMapping(value = "/sendDirect")
//     @ResponseBody
//     @Transactional(rollbackFor = Exception.class)
//     public void sendDirectMsg(){
//         producer.send2DirectTestQueue("this is a test direct message!");
//     }
//
//     @GetMapping(value = "/sendDirectA")
//     @ResponseBody
//     @Transactional(rollbackFor = Exception.class)
//     public void sendTopicAMsg(){
//         producer.send2TopicTestAQueue("this is a test topic aaa message!");
//     }
//
//     @GetMapping(value = "/sendTopicB")
//     @ResponseBody
//     @Transactional(rollbackFor = Exception.class)
//     public void sendTopicBMsg(){
//         producer.send2TopicTestBQueue("this is a test topic bbb message!");
//     }
// }
