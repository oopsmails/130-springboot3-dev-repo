package com.oopsmails.springboot.unittest5.notbean;

import com.oopsmails.springboot.unittest5.service.DoThingsBadExampleService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class NotBeanClassA {
    private final DoThingsBadExampleService doThingsBadExampleService;

    public NotBeanClassA(DoThingsBadExampleService doThingsBadExampleService) {
        this.doThingsBadExampleService = doThingsBadExampleService;
    }

    public void doThing() {
        log.info("Real doThing running...");
    }
}
