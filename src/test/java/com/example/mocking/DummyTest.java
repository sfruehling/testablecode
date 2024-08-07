package com.example.mocking;

import com.example.mocking.notsoimportant.Foo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

class DummyTest {

    Foo fooMock = mock(Foo.class);
    Dummy dummy = new Dummy(fooMock);


    @Test
    void simpleExample() {
        when(fooMock.tweakInteger(123)).thenReturn(100);
        int result = dummy.getInteger();
        assertThat(result).isEqualTo(100);
    }

    @Test
    void hiddenSideEffect() {
        // make this test run in just a few milliseconds
        when(fooMock.getTimeToWait()).thenReturn(1L);
        String result = dummy.run();
        assertThat(result).isEqualTo("OK");
    }

    @Test
    void verification() {
        dummy.getInteger();
        // verify that Foo.echoInteger has been called with value 123
        verify(fooMock).tweakInteger(123);
    }

    @Test
    void hardToReproduceResult() {
        // make the test pass by mocking the "magic" method twice, depending on the input
        when(fooMock.magic(1)).thenReturn(2);
        when(fooMock.magic(2)).thenReturn(3);

        int result = dummy.doMagic(1, 2);
        assertThat(result).isEqualTo(42);
    }

    @Test
    void impossibleToReproduceException() {
        when(fooMock.tweakInteger(123)).thenThrow(new ArithmeticException());
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> dummy.getInteger());
    }
}