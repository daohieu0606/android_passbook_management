package com.example.passbook.services;

import android.content.Context;
import android.os.Build;

import com.example.passbook.activities.searchpassbook.SearchPassBookActivity;
import com.example.passbook.activities.searchpassbook.SearchPassbookContract;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ServiceLocator {
    private static ServiceLocator instance;
    private HashMap<Class, Object> services;

    private ServiceLocator() {
        services = new HashMap<>();
    }

    public static synchronized ServiceLocator getInstance(){
        if(instance == null){
            instance = new ServiceLocator();
        }
        return instance;
    }

    public Object getService(Class interfaceName) {
        if(services.containsKey(interfaceName)) {
            return services.get(interfaceName);
        } else {
            return null;
        }
    }

    public void registerService(Class interfaceName, final Class<?> className, Object ... args) {
        if(!services.containsKey(interfaceName)) {
            try {
                Constructor constructor = className.getConstructor(args.getClass());
                try {
                    Object myService = constructor.newInstance(args);
                    services.put(interfaceName, myService);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                return;
            }
        }
    }
}
