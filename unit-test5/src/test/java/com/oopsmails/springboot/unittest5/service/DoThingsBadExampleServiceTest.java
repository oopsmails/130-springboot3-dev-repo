package com.oopsmails.springboot.unittest5.service;

import com.oopsmails.springboot.unittest5.notbean.NotBeanClassA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.mockito.Mockito.*;

class DoThingsBadExampleServiceTest {

    private DoThingsBadExampleService doThingsBadExampleService;

    @BeforeEach
    void setUp() {
        doThingsBadExampleService = new DoThingsBadExampleService();
    }

    @Test
    void testDoThing() {
        // Mock the construction of NotBeanClassA
        try (MockedConstruction<NotBeanClassA> mocked = mockConstruction(NotBeanClassA.class, (mock, context) -> {
            doNothing().when(mock).doThing();
        })) {
            // Act
            doThingsBadExampleService.doThing();

            // Assert
            NotBeanClassA mockInstance = mocked.constructed().get(0);
            verify(mockInstance, times(1)).doThing();
        }
    }
}