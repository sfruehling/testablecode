package com.example.demo1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringManipulatorTest {
    private final StringManipulator stringManipulator = new StringManipulator();

    @Test
    void reverseString_isEmpty_onEmptyString() {
        assertThat(stringManipulator.reverseString("")).isEqualTo("");
    }

    @Test
    void reverseString_ThrowsNullpointerException_onNullString() {
        assertThrows(NullPointerException.class, () -> stringManipulator.reverseString(null));
    }

    @Test
    void reverseString_reversesString() {
        assertThat(stringManipulator.reverseString("abc")).isEqualTo("cba");
    }

    @Test
    void reverseString_returnsCharacter_onCharacterInput() {
        assertThat(stringManipulator.reverseString("a")).isEqualTo("a");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "aa", "abba"})
        // six numbers
    void isPalindrome(String input) {
        assertThat(stringManipulator.isPalindrome(input)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab", "baba"})
        // six numbers
    void isNotPalindrome(String input) {
        assertThat(stringManipulator.isPalindrome(input)).isFalse();
    }

    @Test
    void isPalindrome_() {
        assertThrows(NullPointerException.class, () -> stringManipulator.isPalindrome(null));
    }
}