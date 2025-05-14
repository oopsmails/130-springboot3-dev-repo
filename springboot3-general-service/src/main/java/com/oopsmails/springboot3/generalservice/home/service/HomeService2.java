package com.oopsmails.springboot3.generalservice.home.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HomeService2 {

    public String echo2(String text) {
        log.info("echoing2: {}", text);
        return "echo2: " + text;
    }
}
