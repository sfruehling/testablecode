package com.example.demo1;

import com.example.demo1.notsoimportant.MyDateFormatter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CMyServiceTest {

    private static final long EVEN_TIMESTAMP = 1721233766L;
    private static final long ODD_TIMESTAMP = 1721233767L;
    private static final long MULTIPLE_OF_5_TIMESTAMP = 1721194295L;


    private final MyDateFormatter myDateFormatter = mock(MyDateFormatter.class);
    private final CMyService myService = new CMyService(myDateFormatter);

    @ParameterizedTest
    @MethodSource("provideArguments")
    void getResult_returnsExpectedValue(long firstTimeStamp, long secondTimeStamp, long argument) {
        myService.getResult(firstTimeStamp, secondTimeStamp);
        verify(myDateFormatter).convertToISO8601(argument);
    }

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(ODD_TIMESTAMP, EVEN_TIMESTAMP, ODD_TIMESTAMP),
                Arguments.of(MULTIPLE_OF_5_TIMESTAMP, ODD_TIMESTAMP, ODD_TIMESTAMP),
                Arguments.of(EVEN_TIMESTAMP, ODD_TIMESTAMP, ODD_TIMESTAMP+EVEN_TIMESTAMP)
        );
    }
}
