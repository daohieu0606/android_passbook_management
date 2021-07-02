package com.example.passbook.services;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RegisterServices();
    }

    private void RegisterServices() {
    }
}
