package com.example.mocking;

import com.example.mocking.notsoimportant.Foo;

import java.time.Duration;

// do not change!
public class Dummy {
    private final Foo foo;

    public Dummy(Foo foo) {
        this.foo = foo;
    }

    public int getInteger() {
        try {
            return foo.tweakInteger(123);
        } catch (ArithmeticException e) {
            throw new IllegalStateException(e);
        }
    }

    public int doMagic(int a, int b) {
        int magicA = foo.magic(a);
        int magicB = foo.magic(b);
        if (magicA * 3 == magicB + 3) {
            return 42;
        }
        return magicA + magicB;
    }

    public String run() {
        waitABit(foo.getTimeToWait());
        return "OK";
    }

    private void waitABit(long millis) {
        try {
            if (millis == 0L) {
                Thread.sleep(Duration.ofSeconds(5).toMillis());
            } else {
                Thread.sleep(millis);
            }
        } catch (InterruptedException e) {
            // ignored
        }
    }
}
