package com.example.passbook.activities.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.passbook.services.AppDatabase;

public abstract class BasePresenter implements BaseContract.Presenter {
    protected AppDatabase appDatabase;
    private BaseContract.View view;

    public BasePresenter(BaseContract.View view) {
        appDatabase = Room.databaseBuilder(((AppCompatActivity)view).getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
    }
}
