package com.oopsmails.springboot3.generalservice.home.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oopsmails.springboot3.generalservice.home.dto.EchoResponse;
import com.oopsmails.springboot3.generalservice.home.service.HomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HomeControllerTest3 {
    private MockMvc mockMvc;

    @Mock
    private HomeService homeService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new HomeController(homeService))
                .build();
        objectMapper = new ObjectMapper(); // Initialize ObjectMapper
    }

    @Test
    void getEndpointWithJsonFromFile() throws Exception {
        // Read the JSON file as a String
        Path jsonFilePath = Paths.get("src/test/resources/mockdata/mock-response.json");
        String jsonContent = Files.readString(jsonFilePath);

        // Map the JSON content to EchoResponse
        EchoResponse expectedResponse = objectMapper.readValue(jsonContent, EchoResponse.class);

        // Mock the behavior of HomeService
        when(homeService.echo("test")).thenReturn(jsonContent);

        // Perform the request and map the response to EchoResponse
        String actualJson = mockMvc.perform(get("/home/echo")
                        .param("text", "test"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        EchoResponse actualResponse = objectMapper.readValue(actualJson, EchoResponse.class);

        // Assert the response
        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());

        // Verify that the echo method was called once
        verify(homeService).echo("test");
    }
}