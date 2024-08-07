package com.example.mocking;

import com.example.mocking.notsoimportant.Foo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class Dummy2Test {

    private final Foo mockFoo = mock(Foo.class);
    private final Dummy2 dummy2 = new Dummy2(mockFoo);

    @Test
    void calculate_HasNoInteractionWithFoo() {
        dummy2.calculate();
        // verify that dummy2.calculate() does not interact with mockFoo
    }

    @Test
    void calculate2_Has2InteractionsWithFooMagicFirstIs2SecondIs3() {
        dummy2.calculate2();

        // verify that dummy2.calculate2() interacts first with mockFoo.magic(2) and then with mockFoo.magic(3)
    }

    @Test
    void calculate3_Has5InteractionsWithFooMagic() {

        dummy2.calculate3();

        // verify that dummy2.calculate3() interacts 5 times with mockFoo.magic(2)
    }


    @Test
    void calculate4_returns3OnArithmeticException() {
        // train foo.voidMethod() to throw ArithmeticException

        assertThat(dummy2.calculate4()).isEqualTo(3);
    }
}