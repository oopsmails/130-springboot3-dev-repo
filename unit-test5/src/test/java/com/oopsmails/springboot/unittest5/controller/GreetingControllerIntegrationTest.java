package com.oopsmails.springboot.unittest5.controller;

import com.oopsmails.springboot.unittest5.service.GreetingService;
import com.oopsmails.springboot.unittest5.service.SampleService;
import com.oopsmails.springboot.unittest5.service.TimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GreetingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingService greetingService;

    @MockBean
    private TimeService timeService;

    @MockBean
    private SampleService sampleService;

    @Test
    void testGreet_withFullSpringContext() throws Exception {
        when(greetingService.getGreeting()).thenReturn("Welcome");
        when(timeService.getCurrentTime()).thenReturn("02:00 PM");
        when(sampleService.getSuffix()).thenReturn(" [integrated]");

        mockMvc.perform(get("/api/greet"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome at 02:00 PM [integrated]"));
    }
}
