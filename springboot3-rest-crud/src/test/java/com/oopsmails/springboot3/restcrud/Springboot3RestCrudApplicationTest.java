package com.oopsmails.springboot3.restcrud;

import com.oopsmails.springboot3.restcrud.book.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

@WebAppConfiguration
@SpringBootTest(classes = { //
        Springboot3RestCrudApplicationTest.class, //
        Springboot3RestCrudApplicationTest.Springboot3RestCrudApplicationTestConfig.class, //
})
class Springboot3RestCrudApplicationTest {
    @Autowired
    protected Clock clock;

    @Test
    void contextLoads() {
        assertThat(this.clock).isNotNull();
    }

    @TestConfiguration
    @ComponentScan("com.oopsmails.springboot3.restcrud.book")
    public static class Springboot3RestCrudApplicationTestConfig {
        @MockBean
        public BookRepository bookRepository;

        @Bean
        public Clock clock() {
            LocalDateTime mockNow = LocalDateTime.of(2019, Month.FEBRUARY, 20, 10, 00, 20);
            Clock result = Clock.fixed(mockNow.atZone(ZoneId.of("Canada/Eastern")).toInstant(), ZoneId.of("Canada/Eastern"));

            return result;
        }
    }
}
