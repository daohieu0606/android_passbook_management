package com.example.passbook.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static Date createDate(int year, int month, int dayOfMonth) {
        String dateStr = String.format("%d-%d-%d", year, month, dayOfMonth);

        return parseDate(dateStr);
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateToString(Date date) {
        return dateToString(date, Constant.SHORT_DATE);
    }

    public static String dateToString(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
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

        result = plusDates(date, 1);

        return result;
    }

    public static Date plusDates(Date date, int numOfDays) {    //TODO: check if plus a number greater than 30
        Date result = null;

        Calendar calendar = toCalendar(date);

        calendar.add(Calendar.DAY_OF_YEAR, numOfDays);

        result = calendar.getTime();

        return result;
    }

    public static long subDates(Date small, Date big){
        long diff = big.getTime() - small.getTime();

        TimeUnit time = TimeUnit.DAYS;
        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);

        return diffrence;
    }

    public static void setNewThemeColor(Activity activity, int red, int green, int blue) {
        int colorStep = 15;
        red = Math.round(red / colorStep) * colorStep;
        green = Math.round(green / colorStep) * colorStep;
        blue = Math.round(blue / colorStep) * colorStep;

        /*String stringColor = Integer.toHexString(Color.rgb(red, green, blue)).substring(2);
        SharedPreferences.Editor editor = activity.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit();
        editor.putString(KEY, stringColor);
        editor.apply();*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) activity.recreate();
        else {
            Intent i = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(i);
        }
    }

}
