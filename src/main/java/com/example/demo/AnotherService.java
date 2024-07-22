package com.example.demo;

import java.io.IOException;

public class AnotherService {

    private final MyFileReader myFileReader;
    private final MyFormatter myFormatter;

    public AnotherService(MyFileReader myFileReader, MyFormatter myFormatter) {
        this.myFileReader = myFileReader;
        this.myFormatter = myFormatter;
    }

    public String getResult(String path1, String path2) {
        try {
            long timestampStr1 = myFileReader.readFile(path1);
            long timestampStr2 = myFileReader.readFile(path2);
            if (timestampStr2 % 2 == 0) {
                return myFormatter.convertToISO8601(timestampStr1);
            }
            if (timestampStr1 % 5 == 0) {
                return myFormatter.convertToISO8601(timestampStr2);
            }
            return myFormatter.convertToISO8601(timestampStr1 + timestampStr2);
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Ungültiger Timestamp: " + e.getMessage());
        }
        return null;
    }

}
