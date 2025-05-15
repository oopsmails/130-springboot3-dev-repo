package com.oopsmails.springboot.unittest5.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TimeService {
    public String getCurrentTime() {
        return java.time.LocalTime.now().toString();
    }

    public String getCurrentTime(String format) {
        return java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern(format));
    }

    public String getCurrentTime(String format, String timezone) {
        java.time.ZoneId zoneId = java.time.ZoneId.of(timezone);
        return java.time.ZonedDateTime.now(zoneId).format(java.time.format.DateTimeFormatter.ofPattern(format));
    }
}
