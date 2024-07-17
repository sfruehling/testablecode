package com.example.demo;

import com.example.demo.notsoimportant.Bar;
import com.example.demo.notsoimportant.BazWrapper;
import com.example.demo.notsoimportant.Foo;

class SomeService {
    private Foo foo;
    private final Bar bar;
    private final BazWrapper baz;

    public SomeService(Foo foo, Bar bar, BazWrapper baz) {
        this.foo = foo;
        this.bar = bar;
        this.baz = baz;
    }

    public int someMethod() {
        return foo.foo() + bar.bar() +baz.getMagic();
    }

}
