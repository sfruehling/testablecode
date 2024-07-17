package com.example.demo.notsoimportant;

public class Bar {
    private final int number;

    public Bar(int number) {

        this.number = number;
    }

    public int bar() {
        return (int) (Math.random() * number);
    }
}
