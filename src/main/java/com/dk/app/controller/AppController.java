package com.dk.app.controller;

import com.dk.app.entity.Student;
import com.dk.app.repo.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "app")
public class AppController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(path = "/get-time")
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }

    @GetMapping(path = "/students")
    public List<Student> students() {
        return studentRepository.findAll();
    }
}
