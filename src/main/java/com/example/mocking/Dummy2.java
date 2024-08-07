package com.example.mocking;

import com.example.mocking.notsoimportant.Foo;

public class Dummy2 {

    private final Foo foo;

    public Dummy2(Foo foo) {
        this.foo = foo;
    }

    public String calculate() {
        return "ERROR";
    }

    public void calculate2() {
        foo.magic(2);
        foo.magic(3);
    }

    public void calculate3() {
        for(int i = 0; i < 5; i++) {
            foo.magic(2);
        }
    }

    public int calculate4() {
        try {
            foo.voidMethod();
        } catch (ArithmeticException e) {
            return 3;
        }
        return 5;
    }

    public int calculate5() {
        double random = foo.magic(10);
        if(random % 2 == 0) {
            return foo.magic(2);
        }
        return foo.magic(2) + foo.magic(3);
    }
}
