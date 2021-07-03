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

    public <T> T getService(Class<T> interfaceName) {
        if(services.containsKey(interfaceName)) {
            return (T) services.get(interfaceName);
        } else {
            return null;
        }
    }

    public void registerService(Class interfaceName, final Class<?> className, Object ... args)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        if(!services.containsKey(interfaceName)) {
            try {
                Constructor constructor = null;

                if(args == null) {
                    constructor = className.getConstructor();
                }else {
                    constructor = className.getConstructor(args.getClass());
                }

                try {
                    Object myService = null;

                    if(args == null) {
                        myService = constructor.newInstance();
                    } else {
                        myService = constructor.newInstance(args);
                    }
                    services.put(interfaceName, myService);
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    throw e;
                }
            } catch (NoSuchMethodException | InstantiationException | InvocationTargetException e) {
                throw e;
            }
        }
    }
}
