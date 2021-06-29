package com.example.passbook.activities.form;

import com.example.passbook.activities.base.BaseContract;

public interface FormContract {
    interface View extends BaseContract.View {
        void handleSuccess();
        void handleFailed();
    }

    interface Presenter extends BaseContract.Presenter {
        void handleSubmit(Object... objects);
    }
}
