package com.example.passbook.services;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.passbook.data.enums.LanguageType;
import com.example.passbook.services.impl.CurrentStateService;
import com.example.passbook.services.impl.MenuFunctionService;
import com.example.passbook.utils.Constant;
import com.example.passbook.utils.LocaleHelper;

import java.util.Locale;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerServices();

        intData();
    }

    private void intData() {
        initLanguage();
    }

    private void initLanguage() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.SETTING, Context.MODE_PRIVATE);
        String stringLanguage = sharedPreferences
                .getString(Constant.LANGUAGE, Constant.ENGLISH_TYPE_STRING);

        LanguageType currentLanguage =  LanguageType.fromString(stringLanguage);

        ServiceLocator.getInstance()
                .getService(ICurrentStateService.class)
                .setCurrentLanguageType(currentLanguage);
    }

    private void registerServices() {
        try {
            ServiceLocator.getInstance().registerService(ICurrentStateService.class, CurrentStateService.class, null);
            ServiceLocator.getInstance().registerService(IMenuFunctionService.class, MenuFunctionService.class, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
