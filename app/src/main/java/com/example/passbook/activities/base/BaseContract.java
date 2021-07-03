package com.example.passbook.activities.base;

import android.content.Intent;

import com.example.passbook.data.enums.ThemeType;

public interface BaseContract {
    interface View {
        void moveToAnotherActivity(Intent intent);
    }

    interface Presenter {
        void changeTheme(ThemeType themeType);
    }
}
