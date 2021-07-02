package com.example.passbook.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.passbook.data.enums.ThemeType;

import org.apache.commons.lang3.StringUtils;

public class ThemeExtension {
    public static void setTheme(AppCompatActivity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.SETTING, Context.MODE_PRIVATE);
        String stringTheme = sharedPreferences.getString(Constant.THEME, "");

        if(!StringUtils.isEmpty(stringTheme)) {
            activity.setTheme(activity.getResources().getIdentifier(stringTheme, Constant.STYLE, activity.getPackageName()));
        }
    }

    public static void changeTheme(AppCompatActivity activity, ThemeType themeType) {
        String stringTheme = getThemeString(themeType);

        if(!StringUtils.isEmpty(stringTheme)) {
            saveThem(activity, stringTheme);

            reloadActivity(activity);
        }
    }

    private static void reloadActivity(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) activity.recreate();
        else {
            Intent i = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(i);
        }
    }

    private static void saveThem(Context context, String stringTheme) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.SETTING, Context.MODE_PRIVATE).edit();
        editor.putString(Constant.THEME, stringTheme);
        editor.apply();
    }

    private static String getThemeString(ThemeType themeType) {
        String stringTheme = "";

        switch (themeType) {
            case BLUE:
                stringTheme = Constant.BLUE_THEME;
                break;

            case PINK:
                stringTheme = Constant.PINK_THEME;
                break;

            case YELLOW:
                stringTheme = Constant.YELLOW_THEME;
                break;
        }

        return stringTheme;
    }
}
