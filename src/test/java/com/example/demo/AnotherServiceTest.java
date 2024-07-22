package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("FieldCanBeLocal")
@ExtendWith(OutputCaptureExtension.class)
class AnotherServiceTest {

    private final String TIMESTAMP_20240717_162926 = "1721233766";
    private final String TIMESTAMP_ENDING_ON5_20240717_073135 = "1721194295";

    private final String TIMESTAMP_20240717_162927 = "1721233767";
    private final String TIMESTAMP_20240717_163527 = "1721190927";
    private final String TIMESTAMP_20240717_072927 = "1721194167";
    private final String TIMESTAMP_20240717_073127 = "1721194287";
    private final String TIMESTAMP_20240717_070127 = "1721192487";

    @TempDir
    private Path tempDir;

    private final AnotherService anotherService = new AnotherService();


    @Test
    void evenFile2Timestamp_WithFile1TimeStampLower30MinAndEvenHour_ReturnsYearOfFile1Content() throws IOException {
        final String tempfile1 = createTempFileWithContent(TIMESTAMP_20240717_162927)
                .toAbsolutePath()
                .toString();
        final String tempFile2 = createTempFileWithContent(TIMESTAMP_20240717_162926)
                .toAbsolutePath()
                .toString();

        assertThat(anotherService.getResult(tempfile1, tempFile2)).isEqualTo("2024");
    }

    @Test
    void evenFile2Timestamp_WithFile1TimeStampLower30MinAndOddHour_ReturnsISOOfFile1Content() throws IOException {
        final String tempFile1 = createTempFileWithContent(TIMESTAMP_20240717_072927)
                .toAbsolutePath()
                .toString();
        final String tempFile2 = createTempFileWithContent(TIMESTAMP_20240717_162926)
                .toAbsolutePath()
                .toString();

        assertThat(anotherService.getResult(tempFile1, tempFile2)).isEqualTo("2024-07-17T07:29:27");
    }

    @Test
    void evenFile2Timestamp_WithFile1TimeStampGreater30MinAndEvenHourReturnsMonthOfFile1Content() throws IOException {
        final String tempFile1 = createTempFileWithContent(TIMESTAMP_20240717_163527)
                .toAbsolutePath()
                .toString();
        final String tempFile2 = createTempFileWithContent(TIMESTAMP_20240717_162926)
                .toAbsolutePath()
                .toString();

        assertThat(anotherService.getResult(tempFile1, tempFile2)).isEqualTo("Juli");
    }

    @Test
    void evenFile2Timestamp_WithFile1TimeStampGreater30MinAndOddHourReturnsMonthOfFile1Content() throws IOException {
        final String tempFile1 = createTempFileWithContent(TIMESTAMP_20240717_073127)
                .toAbsolutePath()
                .toString();
        final String tempFile2 = createTempFileWithContent(TIMESTAMP_20240717_162926)
                .toAbsolutePath()
                .toString();

        assertThat(anotherService.getResult(tempFile1, tempFile2)).isEqualTo("Juli");
    }

    @Test
    void File1TimestampEndingOn5_WithFile2TimeStampGreater30MinAndEvenHourReturnsMonthOfFile1Content() throws IOException {
        final String tempFile1 = createTempFileWithContent(TIMESTAMP_ENDING_ON5_20240717_073135)
                .toAbsolutePath()
                .toString();
        final String tempFile2 = createTempFileWithContent(TIMESTAMP_20240717_163527)
                .toAbsolutePath()
                .toString();

        assertThat(anotherService.getResult(tempFile1, tempFile2)).isEqualTo("Juli");
    }

    @Test
    void File1TimestampEndingOn5_WithFile2TimeStampLower30MinAndEvenHourReturnsYearOfFile1Content() throws IOException {
        final String tempFile = createTempFileWithContent(TIMESTAMP_ENDING_ON5_20240717_073135)
                .toAbsolutePath()
                .toString();
        final String tempFile2 = createTempFileWithContent(TIMESTAMP_20240717_162927)
                .toAbsolutePath()
                .toString();

        assertThat(anotherService.getResult(tempFile, tempFile2)).isEqualTo("2024");
    }

    @Test
    void File1TimestampEndingOn5_WithFile2TimeStampLower30MinAndOddHourReturnsIsoOfFile1Content() throws IOException {
        final String tempFile = createTempFileWithContent(TIMESTAMP_ENDING_ON5_20240717_073135)
                .toAbsolutePath()
                .toString();
        final String tempFile2 = createTempFileWithContent(TIMESTAMP_20240717_072927)
                .toAbsolutePath()
                .toString();

        assertThat(anotherService.getResult(tempFile, tempFile2)).isEqualTo("2024-07-17T07:29:27");
    }

    @Test
    void willReturnNullOnFile1NotFound(CapturedOutput output) throws IOException {
        final String tempFile = createTempFileWithContent("1721252866")
                .toAbsolutePath() + "willNotFindIt";
        final String tempFile2 = createTempFileWithContent(TIMESTAMP_20240717_162926)
                .toAbsolutePath()
                .toString();

        assertThat(anotherService.getResult(tempFile, tempFile2)).isNull();
        assertThat(output.getAll()).contains("Fehler beim Lesen der Datei: ");
    }

    @Test
    void willReturnNullOnFile2NotFound(CapturedOutput output) throws IOException {
        final String tempFile = createTempFileWithContent("1721252866")
                .toAbsolutePath().toString() ;
        final String tempFile2 = createTempFileWithContent(TIMESTAMP_20240717_162926)
                .toAbsolutePath() + "willNotFindIt";

        assertThat(anotherService.getResult(tempFile, tempFile2)).isNull();
        assertThat(output.getAll()).contains("Fehler beim Lesen der Datei: ");
    }

    @Test
    void returnsNullIfFile1ContentCannotBeRead(CapturedOutput output) throws IOException {
        final String tempFile = createTempFileWithContent("canNotReadContent")
                .toAbsolutePath()
                .toString();
        final String tempFile2 = createTempFileWithContent(TIMESTAMP_20240717_162926)
                .toAbsolutePath()
                .toString();

        assertThat(anotherService.getResult(tempFile,tempFile2)).isNull();
        assertThat(output.getAll()).contains("Ungültiger Timestamp: ");
    }

    @Test
    void returnsNullIfFile2ContentCannotBeRead(CapturedOutput output) throws IOException {
        final String tempFile = createTempFileWithContent(TIMESTAMP_20240717_162926)
                .toAbsolutePath()
                .toString();
        final String tempFile2 = createTempFileWithContent("canNotReadContent")
                .toAbsolutePath()
                .toString();

        assertThat(anotherService.getResult(tempFile,tempFile2)).isNull();
        assertThat(output.getAll()).contains("Ungültiger Timestamp: ");
    }

    @Test
    void File1TimestampNotEndingOn5_WithFile2Odd_willReturnMonthOfSum() throws IOException {
        final String tempFile = createTempFileWithContent(TIMESTAMP_20240717_162926)
                .toAbsolutePath()
                .toString();
        final String tempFile2 = createTempFileWithContent(TIMESTAMP_20240717_162927)
                .toAbsolutePath()
                .toString();

        assertThat(anotherService.getResult(tempFile,tempFile2)).isEqualTo("Feb.");
    }
    @Test
    void File1TimestampNotEndingOn5_WithFile2Odd_willReturnYearOfSum() throws IOException {
        final String tempFile = createTempFileWithContent(TIMESTAMP_20240717_162926)
                .toAbsolutePath()
                .toString();
        final String tempFile2 = createTempFileWithContent(TIMESTAMP_20240717_070127)
                .toAbsolutePath()
                .toString();

        assertThat(anotherService.getResult(tempFile,tempFile2)).isEqualTo("2079");
    }

    @Test
    void File1TimestampNotEndingOn5_WithFile2Odd_willReturnIsoOfSum() throws IOException {
        final String tempFile = createTempFileWithContent(TIMESTAMP_20240717_162926)
                .toAbsolutePath()
                .toString();
        final String tempFile2 = createTempFileWithContent("1721195587")
                .toAbsolutePath()
                .toString();

        assertThat(anotherService.getResult(tempFile,tempFile2)).isEqualTo("2079-01-31T23:22:33");
    }


    private Path createTempFileWithContent(String filecontent) throws IOException {
        String filename = UUID.randomUUID().toString();
        final Path tempFile = Files.createFile(tempDir.resolve(filename+".txt"));

        Files.writeString(tempFile, filecontent);
        return tempFile;
    }
}
