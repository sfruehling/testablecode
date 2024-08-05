package com.example.demo1;

public class StringManipulator {
    public String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public boolean isPalindrome(String s) {
        return s.contentEquals(new StringBuilder(s).reverse());
    }
}
