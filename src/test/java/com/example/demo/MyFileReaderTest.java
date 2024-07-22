package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyFileReaderTest {

    @TempDir
    private Path tempDir;

    private final MyFileReader myFileReader = new MyFileReader();

    @Test
    void canReadNumbersFromFile() throws IOException {
        Path path = createTempFileWithContent("123");
        assertThat(myFileReader.readFile(path.toString())).isEqualTo(123L);
    }

    @Test
    void throwsNumberFormatException() throws IOException {
        Path path = createTempFileWithContent("abc");
        NumberFormatException exception = assertThrows(NumberFormatException.class,
                () -> myFileReader.readFile(path.toString()));
        assertThat(exception).hasMessage("For input string: \"abc\"");
    }

    @Test
    void throwsIOException() throws IOException {
        Path path = createTempFileWithContent("132");
        IOException exception = assertThrows(IOException.class,
                () -> myFileReader.readFile(path.toString() + "doesnotexist"));
        assertThat(exception).hasMessageContaining("doesnotexist");
    }

    private Path createTempFileWithContent(String filecontent) throws IOException {
        String filename = UUID.randomUUID().toString();
        final Path tempFile = Files.createFile(tempDir.resolve(filename + ".txt"));

        Files.writeString(tempFile, filecontent);
        return tempFile;
    }
}
