package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

class AnotherServiceTest {

    @TempDir
    private Path tempDir;

    private final AnotherService anotherService = new AnotherService();

    @Test
    void firstTest() throws IOException {

        String result = anotherService.getResult("", "");

    }

    private String createTempFileWithContent(String filecontent) throws IOException {
        String filename = UUID.randomUUID().toString();
        final Path tempFile = Files.createFile(tempDir.resolve(filename+".txt"));

        Files.writeString(tempFile, filecontent);
        return tempFile.toAbsolutePath().toString();
    }
}