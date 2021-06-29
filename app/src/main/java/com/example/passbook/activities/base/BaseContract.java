package com.example.passbook.activities.base;

import android.content.Intent;

public interface BaseContract {
    interface View {
        void moveToAnotherActivity(Intent intent);
    }

    interface Presenter {

    }
}
