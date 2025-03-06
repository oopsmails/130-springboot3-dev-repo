package com.oopsmails.springboot3.generalservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oopsmails.springboot3.generalservice.home.service.HomeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@WebAppConfiguration
@SpringBootTest
public class Springboot3GeneralServiceApplicationTest {
    @Autowired
    protected HomeService homeService;

    @Test
    void contextLoads() {
        assertThat(this.homeService).isNotNull();
    }
}
