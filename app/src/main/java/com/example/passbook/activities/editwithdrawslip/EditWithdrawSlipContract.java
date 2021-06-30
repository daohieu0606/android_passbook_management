package com.example.passbook.activities.editwithdrawslip;

import com.example.passbook.activities.form.FormContract;

public interface EditWithdrawSlipContract {
    interface View extends FormContract.View {
        void setPassbookIsNotExistError();
        void setPassbookIsClosedError();
        void setCustomerIdWrongError();
        void setMinPeriodError(int term);
        void setOverDepositError(int currentDeposit);
        void notifyInterest(float interest);
    }

    interface Presenter extends FormContract.Presenter {
    }
}
