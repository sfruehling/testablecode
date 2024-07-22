package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class MyFormatterTest {
    private final MyFormatter myFormatter = new MyFormatter();

    @Test
    void returnsMonthOnMinuteGreater30() {
        long TIMESTAMP_20240717_073127 = 1721194287L;
        Assertions.assertThat(myFormatter.convertToISO8601(TIMESTAMP_20240717_073127)).isEqualTo("Juli");
    }

    @Test
    void returnsYearOnMinute30orLowerAndEvenHour() {
        long TIMESTAMP_20240717_083027 = 1721197827L;
        Assertions.assertThat(myFormatter.convertToISO8601(TIMESTAMP_20240717_083027)).isEqualTo("2024");
    }

    @Test
    void returnsISODateOnMinute30orLowerAndOddHour() {
        long TIMESTAMP_20240717_073027 = 1721194227L;
        Assertions.assertThat(myFormatter.convertToISO8601(TIMESTAMP_20240717_073027)).isEqualTo("2024-07-17T07:30:27");
    }
}
