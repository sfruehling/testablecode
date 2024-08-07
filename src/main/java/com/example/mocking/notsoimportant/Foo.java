package com.example.mocking.notsoimportant;

public class Foo {
    // do not change!

    public int tweakInteger(int input) {
        if (System.currentTimeMillis() % 100 == 0) {
            return input * 2;
        }
        return input;
    }

    public long getTimeToWait() {
        return 100;
    }

    public int magic(int input) {
        return (int) (Math.random() * input);
    }
}
