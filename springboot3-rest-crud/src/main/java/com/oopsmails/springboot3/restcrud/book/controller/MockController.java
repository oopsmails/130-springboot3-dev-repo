package com.oopsmails.springboot3.restcrud.book.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
@Slf4j
public class MockController {
    //  curl "http://localhost:8080/mock/testjson?jsonStr=$(python -c 'print("a"*9000)')"
    @GetMapping("testjson")
    public String testGet(@RequestParam("jsonStr") String jsonStr) {
        log.info("@GetMapping(\"/test\") ..... GET, jsonStr={}", jsonStr);
        return jsonStr;
    }
}

