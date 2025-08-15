package com.oopsmails.springboot.pdf;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class PdfTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testConvertPdfToHex() throws Exception {
        String pdfPath = "C:\\oopsmails\\130-springboot3-dev-repo\\unit-test5\\src\\test\\java\\com\\oopsmails\\springboot\\pdf\\test1.pdf";
        //            String pdfPath = "test1.pdf";
        //        String pdfPath = getClass().getClassLoader().getResource("/test1.pdf").getPath();

        byte[] pdfBytes = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(pdfPath));
        StringBuilder hexString = new StringBuilder();
        for (byte b : pdfBytes) {
            hexString.append(String.format("%02X", b));
        }

        java.nio.file.Files.write(java.nio.file.Paths.get("test1hex.txt"), hexString.toString().getBytes());
        log.info(hexString.toString());
    }
    @Test
    void testConvertHexToPdf() throws Exception {
        String hexFilePath = "test1hex.txt";
        String outputPdfPath = "test1converted.pdf";

        String hexString = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(hexFilePath)));
        int len = hexString.length();
        byte[] pdfBytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            pdfBytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i+1), 16));
        }

        java.nio.file.Files.write(java.nio.file.Paths.get(outputPdfPath), pdfBytes);
        log.info("PDF file written to {}", outputPdfPath);
    }

    @Test
    void testConvertPdfToEncoded() throws Exception {
        String pdfPath = "C:\\oopsmails\\130-springboot3-dev-repo\\unit-test5\\src\\test\\java\\com\\oopsmails\\springboot\\pdf\\test1.pdf";
        //            String pdfPath = "test1.pdf";
        //        String pdfPath = getClass().getClassLoader().getResource("/test1.pdf").getPath();

        byte[] pdfBytes = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(pdfPath));
        StringBuilder hexString = new StringBuilder();
        for (byte b : pdfBytes) {
            hexString.append(String.format("%02X", b));
        }

        java.nio.file.Files.write(java.nio.file.Paths.get("test1hex.txt"), hexString.toString().getBytes());
        log.info(hexString.toString());
    }
}