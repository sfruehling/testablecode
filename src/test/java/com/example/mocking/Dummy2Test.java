package com.example.mocking;

import com.example.mocking.notsoimportant.Foo;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class Dummy2Test {

    private final Foo mockFoo = mock(Foo.class);
    private final Dummy2 dummy2 = new Dummy2(mockFoo);

    @Test
    void calculate_HasNoInteractionWithFoo() {
        dummy2.calculate();

        verifyNoInteractions(mockFoo);
    }

    @Test
    void calculate2_Has2InteractionsWithFooMagicFirstIs2SecondIs3() {
        InOrder inOrder = inOrder(mockFoo);
        dummy2.calculate2();

        // verify that dummy2.calculate2() interacts first with mockFoo.magic(2) and then with mockFoo.magic(3)
        inOrder.verify(mockFoo).magic(2);
        inOrder.verify(mockFoo).magic(3);

    }

    @Test
    void calculate3_Has5InteractionsWithFooMagic() {

        dummy2.calculate3();

        // verify that dummy2.calculate3() interacts 5 times with mockFoo.magic(2)
        verify(mockFoo, times(5)).magic(2);
    }


    @Test
    void calculate4_returns3OnArithmeticException() {
        // train foo.voidMethod() to throw ArithmeticException
        doThrow(new ArithmeticException()).when(mockFoo).voidMethod();

        assertThat(dummy2.calculate4()).isEqualTo(3);
    }
}