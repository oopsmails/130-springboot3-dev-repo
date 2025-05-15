package com.oopsmails.springboot.unittest5.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GreetingService {
    public String getGreeting() {
        return "Hello, World!";
    }

    public String getGreeting(String name) {
        return "Hello, " + name + "!";
    }

    public String getGreeting(String name, int age) {
        return "Hello, " + name + "! You are " + age + " years old.";
    }
}
