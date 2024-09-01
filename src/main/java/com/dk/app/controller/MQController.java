package com.dk.app.controller;

import com.dk.app.mq.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

//@RestController
@Slf4j
//@RequestMapping(path = "mq")
public class MQController {

    @Autowired
    ProducerService producerService;

    @PostMapping(path = "/post")
    public String postMessageToQueue(String message) {
        producerService.sendToQueue(message);
        return "Successfully posted message";
    }
}
