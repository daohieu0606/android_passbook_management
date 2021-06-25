package com.example.passbook.activities.registerpassbook;

import com.example.passbook.activities.base.FormContract;
import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.PassBook;

public interface RegisterPassbookContract {
    interface View extends FormContract.View {
        void setPassBookIsExistedError();
        void setAmountIsSmallThanRegulationError(int minDepositAmount);
    }

    interface Presenter extends FormContract.Presenter {
    }
}
