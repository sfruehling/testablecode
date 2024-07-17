package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


class AnotherServiceTest {

    @TempDir
    private Path tempDir;

    private final AnotherService anotherService = new AnotherService();


    @Test
    void name() throws IOException {
        final String tempFile = createTempFileWithContent("1721233766")
                .toAbsolutePath()
                .toString();

        Assertions.assertThat(anotherService.getResult(tempFile)).isEqualTo("2024-07-17T18:29:26");
    }

    @Test
    void name1() throws IOException {
        final String tempFile = createTempFileWithContent("1721252866")
                .toAbsolutePath()
                .toString();

        Assertions.assertThat(anotherService.getResult(tempFile)).isEqualTo("Juli");
    }

    @Test
    void name2() throws IOException {
        final String tempFile = createTempFileWithContent("1721252866")
                .toAbsolutePath() + "willNotFindIt";

        Assertions.assertThat(anotherService.getResult(tempFile)).isNull();
    }

    @Test
    void name3() throws IOException {
        final String tempFile = createTempFileWithContent("canNotReadContent")
                .toAbsolutePath()
                .toString();

        Assertions.assertThat(anotherService.getResult(tempFile)).isNull();
    }

    private Path createTempFileWithContent(String filecontent) throws IOException {
        final Path tempFile = Files.createFile(tempDir.resolve("myfile.txt"));

        Files.writeString(tempFile, filecontent);
        return tempFile;
    }


}
