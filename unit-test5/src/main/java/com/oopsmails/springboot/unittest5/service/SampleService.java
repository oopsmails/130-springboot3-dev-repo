package com.oopsmails.springboot.unittest5.service;

import org.springframework.stereotype.Service;

@Service
public class SampleService {
    public String getPrefix() {
        return "prefix";
    }

    public String getSuffix() {
        return "suffix";
    }
}
