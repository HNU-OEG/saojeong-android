package com.example.saojeong.util;

import java.time.LocalDate;
import java.util.Calendar;

public class DateHelper {
    private static final Calendar calendar = Calendar.getInstance();


    public static int getMonth() {
        return calendar.get(Calendar.MONTH) + 1;
    }
}
