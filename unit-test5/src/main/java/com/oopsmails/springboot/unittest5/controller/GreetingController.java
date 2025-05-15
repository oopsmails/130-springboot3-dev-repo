package com.oopsmails.springboot.unittest5.controller;

import com.oopsmails.springboot.unittest5.service.GreetingService;
import com.oopsmails.springboot.unittest5.service.SampleService;
import com.oopsmails.springboot.unittest5.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greet")
public class GreetingController {

    @Autowired // private field
    private GreetingService greetingService;

    private final TimeService timeService;

    private SampleService sampleService;

    public GreetingController(TimeService timeService) {
        this.timeService = timeService;
    }

    @Autowired // setter injection
    public void setSampleService(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping
    public ResponseEntity<String> greet() {
        String greeting = greetingService.getGreeting();
        String time = timeService.getCurrentTime();
        String suffix = sampleService != null ? sampleService.getSuffix() : "";
        return ResponseEntity.ok(greeting + " at " + time + suffix);
    }
}

