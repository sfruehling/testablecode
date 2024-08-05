package com.example.demo1.notsoimportant;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MyDateFormatter {
    public String convertToISO8601(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        if (dateTime.getMinute() > 30) {
            formatter = DateTimeFormatter.ofPattern("MMM");
        } else if (dateTime.getHour() %2 == 0){
            formatter = DateTimeFormatter.ofPattern("yyyy");
        }
        return dateTime.format(formatter);
    }
}
