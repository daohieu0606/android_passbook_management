package com.example.passbook.utils;

import android.provider.ContactsContract;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static String dataToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Date getStartDate(Date date) {
        Date result = null;

        Calendar calendar = toCalendar(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        result = calendar.getTime();

        return result;
    }

    public static Date getEndDate(Date date) {
        Date result = null;

        Calendar calendar = toCalendar(date);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 58);

        result = calendar.getTime();

        return result;
    }

    public static Date getStartMonth(Date date) {
        Date result = null;

        Calendar calendar = toCalendar(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.set(Calendar.DATE, 1);

        result = calendar.getTime();

        return result;
    }

    public static Date getEndMonth(Date date) {
        Date result = null;

        Calendar calendar = toCalendar(date);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 58);

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));

        result = calendar.getTime();

        return result;
    }

    public static Date getNextDate(Date date) {
        Date result = null;

        Calendar calendar = toCalendar(date);

        calendar.add(Calendar.DAY_OF_YEAR, 1);

        result = calendar.getTime();

        return result;
    }

}
