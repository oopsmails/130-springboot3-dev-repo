package com.oopsmails.springboot.datetime;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DateTimeConversionTest {

    @Test
    void testLongToLocalDateTimeUTC() {
        long epochMillis = 1719878400000L; // Example: 2024-07-02T00:00:00Z
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.of("UTC"));

        assertEquals(2024, dateTime.getYear());
        assertEquals(7, dateTime.getMonthValue());
        assertEquals(2, dateTime.getDayOfMonth());
    }

    @Test
    void testLongToLocalDateTime() {
        long epochMillis = 988761600000L; // Example: 2024-07-02T00:00:00Z
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.systemDefault());
        log.info("Converted LocalDateTime: {}", dateTime);
        assertNotNull(dateTime);
    }
}