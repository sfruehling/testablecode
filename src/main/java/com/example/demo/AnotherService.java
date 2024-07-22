package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class AnotherService {


    public String getResult(String path1, String path2) {
        try {
            long timestampStr1 = readFile(path1);
            long timestampStr2 = readFile(path2);
            if(timestampStr2 % 2 == 0){
                return convertToISO8601(timestampStr1);
            }
            if(timestampStr1 % 5 == 0) {
                return convertToISO8601(timestampStr2);
            }
            return convertToISO8601(timestampStr1+timestampStr2);
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Ungültiger Timestamp: " + e.getMessage());
        }
        return null;
    }

    private static long readFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return Long.parseLong(br.readLine().trim());
        }
    }

    private static String convertToISO8601(long timestamp) {
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
