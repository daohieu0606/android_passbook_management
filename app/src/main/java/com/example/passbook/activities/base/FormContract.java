package com.example.passbook.activities.base;

public interface FormContract {
    interface View{
        void handleSuccess();
        void handleFailed();
    }

    interface Presenter {
        void handleSubmit(Object... objects);
    }
}
