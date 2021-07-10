package com.example.passbook.activities.registerpassbook;

import com.example.passbook.activities.form.FormContract;
import com.example.passbook.data.enums.RegisterPassbookScreenType;

public interface RegisterPassbookContract {
    interface View extends FormContract.View {
        void setPassBookIsExistedError();
        void setAmountIsSmallThanRegulationError(int minDepositAmount);
        RegisterPassbookScreenType getViewMode();
    }

    interface Presenter extends FormContract.Presenter {
        int getNextPassbookId();
    }
}
