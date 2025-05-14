package com.oopsmails.springboot3.generalservice.home.controller;

import com.oopsmails.springboot3.generalservice.home.service.HomeService;
import com.oopsmails.springboot3.generalservice.home.service.HomeService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
@Slf4j
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @Autowired
    private HomeService2 homeService2;

    @GetMapping("/echo")
    public String echoString(@RequestParam String text) {
        String result2 = homeService2.echo2(text);
        log.info("result2: {}", result2);
        return homeService.echo(text);
    }
}

