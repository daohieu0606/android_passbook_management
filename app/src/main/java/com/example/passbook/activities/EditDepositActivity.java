package com.example.passbook.activities;

import android.text.InputType;

import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.DepositSlip;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.models.DateTimeModel;
import com.example.passbook.models.TextFieldModel;
import com.example.passbook.utils.Constant;

import java.util.ArrayList;
import java.util.Date;

public class EditDepositActivity extends FormHaveSubmitButtonActivity {

    public EditDepositActivity() {
        title = Constant.DEPOSIT_SLIP;
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
        models.add(new DateTimeModel(Constant.DEPOSIT_DATE,
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
            models.get(0).errorSTr = "Passbook Id is not existed";
            result = false;
        }

        Customer refCustomer = appDatabase.customerDAO().getItem(Integer.valueOf((String) models.get(1).value));
        if (refCustomer == null) {
            models.get(1).isError = true;
            models.get(1).errorSTr = "Customer Id is not existed";
            result = false;
        }

        BankRegulation bankRegulation = appDatabase.bankRegulationDAO().getItem(1);
        int minDepositAmount = bankRegulation != null? bankRegulation.minDepositAmount: 100000;

        if(Integer.valueOf((String) models.get(3).value) < minDepositAmount) {
            models.get(3).isError = true;
            models.get(3).errorSTr = "amount must greater then " + String.valueOf(minDepositAmount);
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
