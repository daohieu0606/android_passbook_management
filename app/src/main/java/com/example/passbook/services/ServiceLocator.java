package com.example.passbook.services;

import android.content.Context;

public class ServiceLocator {
    private Context context;

    private static volatile ServiceLocator instance;


    private ServiceLocator(Context context) {
        this.context = context;
    }
/*
    public ServiceLocator getInstance(Context context) {
        if (instance == null) {
            synchronized (ServiceLocator.class) {
                if (instance == null) {
                    instance = new ServiceLocator(context);
                }
            }
        }
        return instance;
    }

    public void  registerService<T>() {

    }*/
}
