package com.example.passbook.activities.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.passbook.data.enums.ThemeType;
import com.example.passbook.services.AppDatabase;
import com.example.passbook.utils.ThemeExtension;

public class BasePresenter implements BaseContract.Presenter {
    protected AppDatabase appDatabase;
    private BaseContract.View view;

    public BasePresenter(BaseContract.View view) {
        this.view = view;
        appDatabase = Room.databaseBuilder(((AppCompatActivity)view).getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    @Override
    public void changeTheme(ThemeType themeType) {
        ThemeExtension.changeTheme((AppCompatActivity) view, themeType);
    }
}
