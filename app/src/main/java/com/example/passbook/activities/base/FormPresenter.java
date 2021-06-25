package com.example.passbook.activities.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.passbook.services.AppDatabase;

public abstract class FormPresenter {
    protected AppDatabase appDatabase;

    public FormPresenter(AppCompatActivity activity) {
        appDatabase = Room.databaseBuilder(activity.getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
    }
}
