package com.example.passbook.activities.registerpassbook;

import com.example.passbook.activities.form.FormContract;

public interface RegisterPassbookContract {
    interface View extends FormContract.View {
        void setPassBookIsExistedError();
        void setAmountIsSmallThanRegulationError(int minDepositAmount);
    }

    interface Presenter extends FormContract.Presenter {
        int getNextPassbookId();
    }
}
