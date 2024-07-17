package com.example.demo;

import com.example.demo.notsoimportant.Bar;
import com.example.demo.notsoimportant.Baz;
import com.example.demo.notsoimportant.Foo;
import org.springframework.beans.factory.annotation.Autowired;

class SomeService {
    @Autowired
    private Foo foo;

    public int someMethod() {
        Bar bar = new Bar(42);
        return foo.foo() + bar.bar() + Baz.magic();
    }
}
