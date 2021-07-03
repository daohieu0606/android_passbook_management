package com.example.passbook.services;

import android.app.Application;

import com.example.passbook.services.impl.CurrentStateService;
import com.example.passbook.utils.ThemeExtension;

import java.lang.reflect.InvocationTargetException;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RegisterServices();
    }

    private void RegisterServices() {
        try {
            ServiceLocator.getInstance().registerService(ICurrentStateService.class, CurrentStateService.class, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
