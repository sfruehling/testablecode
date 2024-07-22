package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("FieldCanBeLocal")
@ExtendWith(OutputCaptureExtension.class)
class AnotherServiceTest {

    private static final long EVEN_TIMESTAMP = 1721233766L;
    private static final long ODD_TIMESTAMP = 1721233767L;
    private static final long MULTIPLE_OF_5_TIMESTAMP = 1721194295L;

    private final MyFormatter myFormatter = mock(MyFormatter.class);
    private final MyFileReader myFileReader = mock(MyFileReader.class);

    private final AnotherService anotherService = new AnotherService(myFileReader, myFormatter);


    @Test
    void oddFile1_evenFile2_returnsOddTimeStamp() throws IOException {
        when(myFileReader.readFile("odd")).thenReturn(ODD_TIMESTAMP);
        when(myFileReader.readFile("even")).thenReturn(EVEN_TIMESTAMP);
        when(myFormatter.convertToISO8601(ODD_TIMESTAMP)).thenReturn("result");

        String result = anotherService.getResult("odd", "even");

        assertThat(result).isEqualTo("result");
    }

    @Test
    void multipleOf5File1_oddFile2_returnsOddTimeStamp() throws IOException {
        when(myFileReader.readFile("multipleOf5")).thenReturn(MULTIPLE_OF_5_TIMESTAMP);
        when(myFileReader.readFile("odd")).thenReturn(ODD_TIMESTAMP);
        when(myFormatter.convertToISO8601(ODD_TIMESTAMP)).thenReturn("result");

        String result = anotherService.getResult("multipleOf5", "odd");

        assertThat(result).isEqualTo("result");
    }

    @Test
    void evenFile1_oddFile2_returnsSum() throws IOException {
        when(myFileReader.readFile("even")).thenReturn(EVEN_TIMESTAMP);
        when(myFileReader.readFile("odd")).thenReturn(ODD_TIMESTAMP);
        when(myFormatter.convertToISO8601(EVEN_TIMESTAMP + ODD_TIMESTAMP)).thenReturn("result");

        String result = anotherService.getResult("even", "odd");

        assertThat(result).isEqualTo("result");
    }

    @Test
    void willReturnNullOnFile1NotFound(CapturedOutput output) throws IOException {
        when(myFileReader.readFile("path1")).thenThrow(new IOException());

        assertThat(anotherService.getResult("path1", "path2")).isNull();
        assertThat(output.getAll()).contains("Fehler beim Lesen der Datei: ");
    }

    @Test
    void willReturnNullOnFile2NotFound(CapturedOutput output) throws IOException {
        when(myFileReader.readFile("path1")).thenReturn(ODD_TIMESTAMP);
        when(myFileReader.readFile("path2")).thenThrow(new IOException());

        assertThat(anotherService.getResult("path1", "path2")).isNull();
        assertThat(output.getAll()).contains("Fehler beim Lesen der Datei: ");
    }

    @Test
    void returnsNullIfFile1ContentCannotBeRead(CapturedOutput output) throws IOException {
        when(myFileReader.readFile("path1")).thenThrow(new NumberFormatException());

        assertThat(anotherService.getResult("path1", "path2")).isNull();
        assertThat(output.getAll()).contains("Ungültiger Timestamp: ");
    }

    @Test
    void returnsNullIfFile2ContentCannotBeRead(CapturedOutput output) throws IOException {
        when(myFileReader.readFile("path1")).thenReturn(ODD_TIMESTAMP);
        when(myFileReader.readFile("path2")).thenThrow(new NumberFormatException());

        assertThat(anotherService.getResult("path1", "path2")).isNull();
        assertThat(output.getAll()).contains("Ungültiger Timestamp: ");
    }
}
