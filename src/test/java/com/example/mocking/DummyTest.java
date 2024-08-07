package com.example.mocking;

import com.example.mocking.notsoimportant.Foo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;

class DummyTest {

    Foo fooMock = mock(Foo.class);
    Dummy dummy = new Dummy(fooMock);


    @Test
    void simpleExample() {
        int result = dummy.getInteger();
        assertThat(result).isEqualTo(100);
    }

    @Test
    void hiddenSideEffect() {
        // make this test run in just a few milliseconds
        String result = dummy.run();
        assertThat(result).isEqualTo("OK");
    }

    @Test
    void verification() {
        dummy.getInteger();
        // verify that Foo.echoInteger has been called with value 123
    }

    @Test
    void hardToReproduceResult() {
        // make the test pass by mocking the "magic" method twice, depending on the input
        int result = dummy.doMagic(1, 2);
        assertThat(result).isEqualTo(42);
    }

    @Test
    void impossibleToReproduceException() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> dummy.getInteger());
    }
}