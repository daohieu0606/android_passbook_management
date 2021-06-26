package com.example.passbook.activities.editwithdrawslip;

import android.os.Bundle;
import android.text.InputType;

import com.example.passbook.R;
import com.example.passbook.activities.form.FormHaveSubmitButtonActivity;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.data.entitys.WithdrawalSlip;
import com.example.passbook.data.models.DateTimeModel;
import com.example.passbook.data.models.TextFieldModel;

import java.util.ArrayList;
import java.util.Date;

public class EditWithdrawalActivity extends FormHaveSubmitButtonActivity implements EditWithdrawSlipContract.View {
    private EditWithdrawSlipContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getString(R.string.withdraw_slip);

        presenter = new EditWithdrawSlipPresenter(this);
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
    protected void getDataFromViewAndCallPresenterHandle() {
        WithdrawalSlip withdrawalSlip = getWithdrawalSlip();
        presenter.handleSubmit(withdrawalSlip);
    }

    private WithdrawalSlip getWithdrawalSlip() {
        WithdrawalSlip withdrawalSlip = new WithdrawalSlip();

        withdrawalSlip.passBookId = Integer.valueOf((String) models.get(0).value);
        withdrawalSlip.customerId = Integer.valueOf((String) models.get(1).value);
        withdrawalSlip.transactionDateTime = (Date) models.get(2).value;
        withdrawalSlip.amount = Integer.valueOf((String) models.get(3).value);

        return withdrawalSlip;
    }
/*
    private float getAmount(PassBook passBook) {
        float result = 0.0f;
        List<PassBookRegulation> passBookRegulations = appDatabase
                                                .passBookRegulationDAO()
                                                .getItemsByType(passBook.passBookType);

        PassBookRegulation lastPassBookRegulation = appDatabase
                                                .passBookRegulationDAO()
                                                .getLastPassBookByType(passBook.passBookType);

        Date currentDate = new Date(passBook.creationDate.getTime());

        while (currentDate.before(lastPassBookRegulation.creationDateTime)){
            //get passbook regulation
            PassBookRegulation curPassBookRegulation = null;
        }

        return result;
    }*/
}
