package ru.sysoevm.carservise.utils;

import java.sql.Date;
import java.util.Calendar;

public class DateUtils {

    private static Date MAX_DATE = Date.valueOf("2100-01-01");
    private static Date MIN_DATE = Date.valueOf("1900-01-01");

    public static Date generate(int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(field, amount);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date now() {
        return new Date(System.currentTimeMillis());
    }

    public static Date maxDate() {
        return MAX_DATE;
    }

    public static Date minDate() {
        return MIN_DATE;
    }

}
