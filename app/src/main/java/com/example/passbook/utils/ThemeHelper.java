package com.example.passbook.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passbook.data.enums.ThemeType;
import com.example.passbook.services.ICurrentStateService;
import com.example.passbook.services.ServiceLocator;

import org.apache.commons.lang3.StringUtils;

public class ThemeHelper {
    public static void setTheme(AppCompatActivity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.SETTING, Context.MODE_PRIVATE);
        String stringTheme = sharedPreferences.getString(Constant.THEME, Constant.DEFAULT_THEME);

        if(!StringUtils.isEmpty(stringTheme)) {
            activity.setTheme(activity.getResources().getIdentifier(stringTheme, Constant.STYLE, activity.getPackageName()));

            ThemeType themeType = ThemeType.fromTextString(stringTheme);
            ServiceLocator.getInstance().getService(ICurrentStateService.class).setCurrentTheme(themeType);
        }
    }

    public static void changeTheme(AppCompatActivity activity, ThemeType themeType) {
        String stringTheme = ThemeType.toTextString(themeType);

        if(!StringUtils.isEmpty(stringTheme)) {
            persistTheme(activity, stringTheme);

            reloadActivity(activity);
        }
    }

    public static void reloadActivity(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) activity.recreate();
        else {
            Intent i = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(i);
        }
    }

    private static void persistTheme(Context context, String stringTheme) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.SETTING, Context.MODE_PRIVATE).edit();
        editor.putString(Constant.THEME, stringTheme);
        editor.apply();
    }
}
