package com.oopsmails.springboot.charsets;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class CharsetsTest {

    @Test
    void testLongToUtf8() {
        String filename = "report — 2025.pdf"; // contains an em dash
        String encodedFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8)
                .replace("+", "%20");
        log.info("Encoded filename: {}", encodedFilename);

        String decodedFilename = java.net.URLDecoder.decode(encodedFilename, StandardCharsets.UTF_8);
        log.info("Decoded back filename: {}", decodedFilename);

        assertNotNull(encodedFilename);
    }
}