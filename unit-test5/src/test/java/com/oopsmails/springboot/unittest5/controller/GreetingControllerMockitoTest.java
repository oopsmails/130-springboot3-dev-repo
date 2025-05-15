package com.oopsmails.springboot.unittest5.controller;

import com.oopsmails.springboot.unittest5.service.GreetingService;
import com.oopsmails.springboot.unittest5.service.SampleService;
import com.oopsmails.springboot.unittest5.service.TimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GreetingControllerMockitoTest {

    @Mock
    private GreetingService greetingService;

    @Mock
    private TimeService timeService;

    @Mock
    private SampleService sampleService;

    private GreetingController controller;

    @BeforeEach
    void setUp() throws Exception {
        controller = new GreetingController(timeService);

        // Inject private field
        Field field = GreetingController.class.getDeclaredField("greetingService");
        field.setAccessible(true);
        field.set(controller, greetingService);

        // Use public setter for setter injection
        controller.setSampleService(sampleService);
    }

    @Test
    void testGreet_withSetterInjectedService() {
        when(greetingService.getGreeting()).thenReturn("Hi");
        when(timeService.getCurrentTime()).thenReturn("01:00 PM");
        when(sampleService.getSuffix()).thenReturn(" (mock)");

        ResponseEntity<String> response = controller.greet();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hi at 01:00 PM (mock)", response.getBody());
    }
}

