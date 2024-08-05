package com.example.demo1;

import com.example.demo1.notsoimportant.MyDateFormatter;

public class CMyService {


    private final MyDateFormatter myDateFormatter;

    public CMyService(MyDateFormatter myDateFormatter) {
        this.myDateFormatter = myDateFormatter;
    }

    @SuppressWarnings("UnusedReturnValue")
    public String getResult(long timestamp1, long timestamp2) {

        if (timestamp2 % 2 == 0) {
            return myDateFormatter.convertToISO8601(timestamp1);
        }

        if (timestamp1 % 5 == 0) {
            return myDateFormatter.convertToISO8601(timestamp2);
        }
        return myDateFormatter.convertToISO8601(timestamp1 + timestamp2);
    }
}
