package com.dk.app.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;

//@Component
@Slf4j
public class ConsumerService {


    @JmsListener(destination = "test-queue")
    @SendTo("myQueue2")
    public String receiveAndForwardMessageFromQueue(final Message jsonMessage) throws JMSException {
        String messageData = null;
        System.out.println("Received message " + jsonMessage);
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            log.info("Queue MessageData: {}", messageData);
        }
        return messageData;
    }

    @JmsListener(destination = "test-topic")
    @SendTo("myNewTopic")
    public String receiveAndForwardMessageFromTopic(final Message jsonMessage) throws JMSException, JsonProcessingException {
        String messageData = null;
        System.out.println("Received message " + jsonMessage);
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            log.info("Topic MessageData: {}", messageData);
        }
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(messageData);
    }


    @JmsListener(destination = "myNewTopic")
    public void receiveMessageFromTopic(final Message jsonMessage) throws JMSException {
        String messageData = null;
        log.info("Received message in myNewTopic topic {}", jsonMessage);
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            log.info("MessageData in myNewTopic listener:{}", messageData);
        }
    }

    @JmsListener(destination = "myQueue2")
    public void receiveMessageFromQueue(final Message jsonMessage) throws JMSException {
        String messageData = null;
        log.info("Received message in myQueue2 {}", jsonMessage);
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            log.info("MessageData in myQueue2 listener:{}", messageData);
        }
    }

}
