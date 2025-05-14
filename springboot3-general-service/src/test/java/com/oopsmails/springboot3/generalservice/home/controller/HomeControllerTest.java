package com.oopsmails.springboot3.generalservice.home.controller;

import com.oopsmails.springboot3.generalservice.home.service.HomeService;
import com.oopsmails.springboot3.generalservice.home.service.HomeService2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This is a test class for the HomeController.
 * It uses Spring's MockMvc to perform HTTP requests and verify responses.
 *
 * Pros:
 * Uses @SpringBootTest and @MockBean, which means it runs in a Spring context.
 * Automatically wires dependencies, making it easier to test components in a Spring-managed environment.
 * Suitable for integration tests where you want to test the controller with Spring's full context.
 *
 * Cons:
 * Slower because it loads the Spring context.
 * Less isolated, as it depends on the Spring framework.
 */

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HomeService homeService;

    @MockBean
    private HomeService2 homeService2;

    @Test
    void getEndpoint() throws Exception {
        String expectedResponse = "echo: test";

        // Mock the behavior of HomeService
        when(homeService.echo("test")).thenReturn(expectedResponse);

        String actualResponse = mockMvc.perform(get("/home/echo")
                        .param("text", "test"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(expectedResponse, actualResponse);

        // Verify that the echo method was called once
        verify(homeService).echo("test");
    }
}