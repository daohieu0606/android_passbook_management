package com.example.passbook.activities;

import android.text.InputType;

import com.example.passbook.R;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.WithdrawalSlip;
import com.example.passbook.data.models.DateTimeModel;
import com.example.passbook.data.models.TextFieldModel;
import com.example.passbook.utils.Constant;

import java.util.ArrayList;
import java.util.Date;

public class EditWithdrawalActivity extends FormHaveSubmitButtonActivity {

    public EditWithdrawalActivity() {
        title = getString(R.string.withdraw_slip);
    }

    @Override
    protected void initModelAndAdapter() {
        models = new ArrayList<>();

        models.add(new TextFieldModel(getString(R.string.passbook_id),
                null,
                "",
                InputType.TYPE_CLASS_NUMBER));
        models.add(new TextFieldModel(getString(R.string.customer_id),
                null,
                "",
                InputType.TYPE_CLASS_NUMBER));
        models.add(new DateTimeModel(getString(R.string.withdraw_date),
                null,
                ""));
        models.add(new TextFieldModel(getString(R.string.amount),
                null,
                "",
                InputType.TYPE_CLASS_NUMBER));

        adapter = new FormAdapter(this, models);
    }

    @Override
    protected void HandleSubmit() {
        WithdrawalSlip withdrawalSlip = getWithdrawalSlip();

        if (withdrawalSlip != null) {
            int id = (int) appDatabase.transactionFormDAO().insertItem(withdrawalSlip);
            withdrawalSlip.Id = id;

            PassBook refPassBook = appDatabase.passBookDAO().getItem(withdrawalSlip.passBookId);

            if (refPassBook != null) {
                int newAmount = refPassBook.amount - withdrawalSlip.amount;
                refPassBook.amount = newAmount;
                appDatabase.passBookDAO().updateOrInsertItem(refPassBook);
            }
        }
    }

    private WithdrawalSlip getWithdrawalSlip() {
        WithdrawalSlip withdrawalSlip = new WithdrawalSlip();

        withdrawalSlip.passBookId = Integer.valueOf((String) models.get(0).value);
        withdrawalSlip.customerId = Integer.valueOf((String) models.get(1).value);
        withdrawalSlip.transactionDateTime = (Date) models.get(2).value;
        withdrawalSlip.amount = Integer.valueOf((String) models.get(3).value);

        return withdrawalSlip;
    }

    private boolean manualCheck() {
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