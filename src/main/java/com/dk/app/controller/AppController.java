package com.dk.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping(path = "app")
public class AppController {

    @GetMapping(path = "/get-time")
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }
}
