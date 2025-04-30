package com.oopsmails.springboot.unittest5.service;

import com.oopsmails.springboot.unittest5.notbean.NotBeanClassA;
import org.springframework.stereotype.Service;

@Service
public class DoThingsBadExampleService {
    public void doThing() {
        NotBeanClassA notBeanClassA = new NotBeanClassA(this); // This is what we want to mock
        notBeanClassA.doThing();
    }
}
