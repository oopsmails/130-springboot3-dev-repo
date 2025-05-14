package com.oopsmails.springboot3.generalservice.home.controller;

import com.oopsmails.springboot3.generalservice.home.service.HomeService;
import com.oopsmails.springboot3.generalservice.home.service.HomeService2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Pros:
 * Uses MockMvcBuilders.standaloneSetup, which does not require a Spring context.
 * Faster and more isolated, focusing only on the controller and its dependencies.
 * Ideal for unit tests where you want to test the controller in isolation.
 *
 * Cons:
 * Requires manual setup of mocks and dependencies.
 * Does not test the controller in a real Spring environment.
 */

class HomeControllerTest2 {
    private MockMvc mockMvc;

    @Mock
    private HomeService homeService;

    @Mock
    private HomeService2 homeService2;

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.openMocks(this); // Initialize mocks
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(new HomeController(homeService)) // Inject mock manually
//                .build();

        HomeController homeController = new HomeController(homeService);

        // Use reflection to set the private field 'homeService2'
        java.lang.reflect.Field field = HomeController.class.getDeclaredField("homeService2");
        field.setAccessible(true);
        field.set(homeController, homeService2);

        mockMvc = MockMvcBuilders
                .standaloneSetup(homeController)
                .build();
    }

    @Test
    void getEndpoint() throws Exception {
        String expectedResponse = "echo: test";

        when(homeService2.echo2("test")).thenReturn(expectedResponse);

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

//    @Test
//    void getEndpointWithDifferentText() throws Exception {
//        String expectedResponse = "echo: different text";
//
//        // Mock the behavior of HomeService
//        when(homeService.echo("different text")).thenReturn(expectedResponse);
//
//        String actualResponse = mockMvc.perform(
//                        get("/home/echo")          // or post(), put(), delete()
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content("{\"key\":\"value\"}")  // for POST/PUT
//                                .header("Authorization", "Bearer token"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.field").value("expected"))
//                .andExpect(content().json(expectedJson))
//                .andDo(print());  // useful for debugging
//
//        assertEquals(expectedResponse, actualResponse);
//
//        // Verify that the echo method was called once
//        verify(homeService).echo("different text");
//    }
}