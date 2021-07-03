package com.example.passbook.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import com.example.passbook.data.enums.LanguageType;
import com.example.passbook.services.ICurrentStateService;
import com.example.passbook.services.ServiceLocator;

import java.util.Locale;

public class LocaleHelper {
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    // the method is used to set the language at runtime
    public static Context setLocale(Context context, LanguageType languageType) {
        Context result = null;
        persist(context, languageType);

        // updating the language for devices above android nougat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = updateResources(context, languageType);
        } else {
            // for devices having lower version of android os
            result = updateResourcesLegacy(context, languageType);
        }

        ServiceLocator.getInstance()
                .getService(ICurrentStateService.class)
                .setCurrentLanguageType(languageType);

        return result;
    }

    private static void persist(Context context, LanguageType languageType) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.SETTING, Context.MODE_PRIVATE).edit();
        editor.putString(Constant.LANGUAGE, languageType.toString());
        editor.apply();
    }

    // the method is used update the language of application by creating
    // object of inbuilt Locale class and passing language argument to it
    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, LanguageType languageType) {
        Locale locale = new Locale(languageType.toString());
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);

        return context.createConfigurationContext(configuration);
    }


    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, LanguageType languageType) {
        Locale locale = new Locale(languageType.toString());
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }
}