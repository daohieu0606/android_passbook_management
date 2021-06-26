package com.example.passbook.activities.editwithdrawslip;

import com.example.passbook.activities.form.FormPresenter;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.WithdrawalSlip;

public class EditWithdrawSlipPresenter extends FormPresenter implements EditWithdrawSlipContract.Presenter {
    private EditWithdrawSlipContract.View view;

    public EditWithdrawSlipPresenter(EditWithdrawSlipContract.View view) {
        super(view);

        this.view = view;
    }

    @Override
    protected void saveData(Object... objects) {
        WithdrawalSlip withdrawalSlip = (WithdrawalSlip) objects[0];

        int id = (int) appDatabase.transactionFormDAO().insertItem(withdrawalSlip);
        withdrawalSlip.Id = id;

        PassBook refPassBook = appDatabase.passBookDAO().getItem(withdrawalSlip.passBookId);

        if (refPassBook != null) {
            int newAmount = refPassBook.amount - withdrawalSlip.amount;
            refPassBook.amount = newAmount;
            appDatabase.passBookDAO().updateOrInsertItem(refPassBook);
        }
    }

    @Override
    protected boolean manualCheck(Object... objects) {
        WithdrawalSlip withdrawalSlip = (WithdrawalSlip) objects[0];
        boolean result = false;

        checkMinNumOfDays();
        checkInfor();

        return result;
    }

    private boolean checkMinNumOfDays() {
        boolean result = false;

        return result;
    }

    private boolean checkInfor() {
        boolean result = false;

        return result;
    }
}
