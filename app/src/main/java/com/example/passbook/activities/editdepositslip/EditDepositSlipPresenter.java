package com.example.passbook.activities.editdepositslip;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passbook.R;
import com.example.passbook.activities.base.FormPresenter;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.DepositSlip;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.enums.PassbookState;

public class EditDepositSlipPresenter extends FormPresenter implements EditDepositSlipContract.Presenter {
    private EditDepositSlipContract.View view;

    public EditDepositSlipPresenter(EditDepositSlipContract.View view) {
        super((AppCompatActivity) view);

        this.view = view;
    }

    @Override
    public void handleSubmit(Object... objects) {
        DepositSlip depositSlip = (DepositSlip) objects[0];

        if(manualCheck(depositSlip)) {
            long id = appDatabase.transactionFormDAO().insertItem(depositSlip);

            PassBook refPassbook = appDatabase.passBookDAO().getItem(depositSlip.passBookId);

            int newAmount = refPassbook.amount + depositSlip.amount;
            refPassbook.amount = newAmount;
            appDatabase.passBookDAO().updateOrInsertItem(refPassbook);

            view.handleSuccess();
        } else {
            view.handleFailed();
        }
    }

    private boolean manualCheck(DepositSlip depositSlip) {
        boolean result = true;

        PassBook refPassbook = appDatabase.passBookDAO().getItem(depositSlip.passBookId);
        if (refPassbook == null) {
            view.setPassbookIsNotExistError();
            result = false;

        } else if(refPassbook.passBookType != PassBookType.INFINITE) {
            view.setPassbookMustBeInfiniteTypeError();
            result = false;

        } else if(refPassbook.passbookState == PassbookState.CLOSED) {
            view.setPassbookIsClosedError();
            result = false;

        }

        if (depositSlip.customerId != refPassbook.customerId) {
            view.setCustomerIdWrongError();
            result = false;
        }

        BankRegulation bankRegulation = appDatabase.bankRegulationDAO().getItem(1);
        int minDepositAmount = bankRegulation != null? bankRegulation.minDepositAmount: 100000;

        if(depositSlip.amount < minDepositAmount) {
            view.setAmountIsSmallThanRegulationError(minDepositAmount);
            result = false;
        }

        return result;
    }
}
