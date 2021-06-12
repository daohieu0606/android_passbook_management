package com.example.passbook.activities;

import android.text.InputType;

import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.WithdrawalSlip;
import com.example.passbook.models.DateTimeModel;
import com.example.passbook.models.TextFieldModel;
import com.example.passbook.utils.Constant;

import java.util.ArrayList;
import java.util.Date;

public class EditWithdrawalActivity extends FormHaveSubmitButtonActivity {

    public EditWithdrawalActivity() {
        title = Constant.WITHDRAWAL_SLIP;
    }

    @Override
    protected void initModelAndAdapter() {
        models = new ArrayList<>();

        models.add(new TextFieldModel(Constant.PASSBOOK_ID,
                null,
                "",
                InputType.TYPE_CLASS_NUMBER));
        models.add(new TextFieldModel(Constant.CUSTOMER_ID,
                null,
                "",
                InputType.TYPE_CLASS_NUMBER));
        models.add(new DateTimeModel(Constant.WITHDRAW_DATE,
                null,
                ""));
        models.add(new TextFieldModel(Constant.AMOUNT,
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
}
