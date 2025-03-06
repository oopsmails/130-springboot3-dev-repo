package com.oopsmails.springboot3.generalservice.home.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
public class HomeServiceTest {

    public String echo(String text) {
        log.info("echoing: {}", text);
        return "echo: " + text;
    }
}
