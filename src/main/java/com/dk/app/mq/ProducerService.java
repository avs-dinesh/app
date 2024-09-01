package com.dk.app.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

//@Component
@Slf4j
public class ProducerService {

    @Value("${spring.activemq.topic}")
    String topic;

    @Value("${spring.activemq.queue}")
    String queue;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void sendToQueue(Object msg) {
        try {
            String jsonObj = objectMapper.writeValueAsString(msg);
            jmsTemplate.send(queue, messageCreator -> {
                TextMessage message = messageCreator.createTextMessage();
                message.setText(jsonObj);
                return message;
            });
        }
        catch (Exception ex) {
            log.error("ERROR in sending message to queue : {}",ex.getMessage());
        }
    }

    public void sendToTopic(Object msg) {
        try {
            String jsonObj = objectMapper.writeValueAsString(msg);
             jmsTemplate.send(topic, messageCreator -> {
                TextMessage message = messageCreator.createTextMessage();
                message.setText(jsonObj);
                return message;
            });
        }
        catch (Exception ex) {
            log.error("ERROR in sending message to queue : {}", ex.getMessage());
        }
    }

}