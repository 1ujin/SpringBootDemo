package com.service;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.stereotype.Service;
//
// @Service
// public class KafkaSender {
//     private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSender.class);
//
//     @Autowired
//     private KafkaTemplate<String, String> kafkaTemplate;
//
//     @Value("${app.topic.name}")
//     private String topic;
//
//     public void send(String message) {
//         LOGGER.info("sending message='{}' to topic='{}'", message, topic);
//         kafkaTemplate.send(topic, message);
//     }
// }
