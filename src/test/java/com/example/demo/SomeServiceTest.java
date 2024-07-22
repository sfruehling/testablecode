package com.example.demo;

import com.example.demo.notsoimportant.Bar;
import com.example.demo.notsoimportant.BazWrapper;
import com.example.demo.notsoimportant.Foo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class SomeServiceTest {

    private final Foo foo = mock(Foo.class);
    private final Bar bar = mock(Bar.class);
    private final BazWrapper baz = mock(BazWrapper.class);
    private SomeService someService = new SomeService(foo, bar, baz);

    @Test
    void name() {
        when(foo.foo()).thenReturn(42);
        when(bar.bar()).thenReturn(0);
        when(baz.getMagic()).thenReturn(0);
        Assertions.assertThat(someService.someMethod()).isEqualTo(42);
    }
}
