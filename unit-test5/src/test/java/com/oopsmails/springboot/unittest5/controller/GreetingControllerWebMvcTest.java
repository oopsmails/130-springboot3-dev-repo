package com.oopsmails.springboot.unittest5.controller;

import com.oopsmails.springboot.unittest5.service.GreetingService;
import com.oopsmails.springboot.unittest5.service.SampleService;
import com.oopsmails.springboot.unittest5.service.TimeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingService greetingService;

    @MockBean
    private TimeService timeService;

    @MockBean
    private SampleService sampleService;

    @Test
    void testGreet_withSetterInjectedService() throws Exception {
        when(greetingService.getGreeting()).thenReturn("Hello");
        when(timeService.getCurrentTime()).thenReturn("12:00 PM");
        when(sampleService.getSuffix()).thenReturn(" [from setter]");

        mockMvc.perform(get("/api/greet"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello at 12:00 PM [from setter]"));
    }
}
