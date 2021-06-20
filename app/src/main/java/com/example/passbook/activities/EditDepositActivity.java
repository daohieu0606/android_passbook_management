package com.example.passbook.activities;

import android.os.Bundle;
import android.text.InputType;

import com.example.passbook.R;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.DepositSlip;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.enums.PassbookState;
import com.example.passbook.data.models.DateTimeModel;
import com.example.passbook.data.models.TextFieldModel;
import com.example.passbook.utils.Constant;

import java.util.ArrayList;
import java.util.Date;

public class EditDepositActivity extends FormHaveSubmitButtonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getString(R.string.deposit_slip);
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
        models.add(new DateTimeModel(getString(R.string.deposit_date),
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
        resetValid();

        if(isValidData()) {
            if(manualCheck()) {
                DepositSlip depositSlip = getDepositForm();

                long id = appDatabase.transactionFormDAO().insertItem(depositSlip);

                PassBook refPassbook = appDatabase.passBookDAO().getItem(depositSlip.passBookId);

                int newAmount = refPassbook.amount + depositSlip.amount;
                refPassbook.amount = newAmount;
                appDatabase.passBookDAO().updateOrInsertItem(refPassbook);

                //TODO: show dialog
                this.finish();
            }
        }

        adapter.notifyDataSetChanged();
    }

    private boolean manualCheck() {
        boolean result = true;

        PassBook refPassbook = appDatabase.passBookDAO().getItem(Integer.valueOf((String) models.get(0).value));
        if (refPassbook == null) {
            models.get(0).isError = true;
            models.get(0).errorSTr = getString(R.string.passbook_is_not_existed);
            result = false;
        } else if(refPassbook.passBookType != PassBookType.INFINITE) {
            models.get(0).isError = true;
            models.get(0).errorSTr = getString(R.string.passbook_must_be_infinite_type);
            result = false;
        } else if(refPassbook.passbookState == PassbookState.CLOSED) {
            models.get(0).isError = true;
            models.get(0).errorSTr = getString(R.string.passbook_have_not_closed_yet);
            result = false;
        }

        int customerId = Integer.valueOf((String) models.get(1).value);
        if (customerId != refPassbook.customerId) {
            models.get(1).isError = true;
            models.get(1).errorSTr = getString(R.string.cus_id_is_wrong);
            result = false;
        }

        BankRegulation bankRegulation = appDatabase.bankRegulationDAO().getItem(1);
        int minDepositAmount = bankRegulation != null? bankRegulation.minDepositAmount: 100000;

        if(Integer.valueOf((String) models.get(3).value) < minDepositAmount) {
            models.get(3).isError = true;
            models.get(3).errorSTr = getString(R.string.amount_must_greater_than) + String.valueOf(minDepositAmount);
            result = false;
        }

        return result;
    }

    private DepositSlip getDepositForm() {
        DepositSlip result = new DepositSlip();

        result.passBookId = Integer.valueOf((String) models.get(0).value);
        result.customerId = Integer.valueOf((String) models.get(1).value);
        result.transactionDateTime = (Date) models.get(2).value;
        result.amount = Integer.valueOf((String) models.get(3).value);

        return result;
    }
}
