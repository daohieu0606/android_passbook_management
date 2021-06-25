package com.example.passbook.activities.editdepositslip;

import com.example.passbook.activities.base.FormContract;

public interface EditDepositSlipContract {
    interface View extends FormContract.View {
        void setPassbookIsNotExistError();
        void setPassbookMustBeInfiniteTypeError();
        void setPassbookIsClosedError();
        void setCustomerIdWrongError();
        void setAmountIsSmallThanRegulationError(int minDepositAmount);
    }

    interface Presenter extends FormContract.Presenter {
    }
}
