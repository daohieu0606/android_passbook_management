package com.example.passbook.activities.splashscreen;

import com.example.passbook.activities.base.BaseContract;
import com.example.passbook.data.models.PassBookItem;

import java.util.List;

public interface SplashScreenContract {
    interface View extends BaseContract.View {
        void setLoadingProgress(int progress);
        void moveToMainActivity();
        void setMessage(String message);
    }

    interface Presenter extends BaseContract.Presenter {
        void loadingData();
    }
}
