package com.example.passbook.activities.editdepositslip;

import android.os.Bundle;
import android.text.InputType;

import com.example.passbook.R;
import com.example.passbook.activities.base.FormHaveSubmitButtonActivity;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.DepositSlip;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.enums.PassbookState;
import com.example.passbook.data.models.DateTimeModel;
import com.example.passbook.data.models.TextFieldModel;

import java.util.ArrayList;
import java.util.Date;

public class EditDepositActivity extends FormHaveSubmitButtonActivity
implements EditDepositSlipContract.View{

    private EditDepositSlipContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getString(R.string.deposit_slip);

        presenter = new EditDepositSlipPresenter(this);
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
    protected void getDataFromViewAndCallPresenterHandle() {
        DepositSlip depositSlip = getDepositFormFromView();

        presenter.handleSubmit(depositSlip);
    }

    private DepositSlip getDepositFormFromView() {
        DepositSlip result = new DepositSlip();

        result.passBookId = Integer.valueOf((String) models.get(0).value);
        result.customerId = Integer.valueOf((String) models.get(1).value);
        result.transactionDateTime = (Date) models.get(2).value;
        result.amount = Integer.valueOf((String) models.get(3).value);

        return result;
    }

    @Override
    public void setPassbookIsNotExistError() {
        models.get(0).isError = true;
        models.get(0).errorSTr = getString(R.string.passbook_is_not_existed);
    }

    @Override
    public void setPassbookMustBeInfiniteTypeError() {
        models.get(0).isError = true;
        models.get(0).errorSTr = getString(R.string.passbook_must_be_infinite_type);
    }

    @Override
    public void setPassbookIsClosedError() {
        models.get(0).isError = true;
        models.get(0).errorSTr = getString(R.string.passbook_have_not_closed_yet);
    }

    @Override
    public void setCustomerIdWrongError() {
        models.get(1).isError = true;
        models.get(1).errorSTr = getString(R.string.cus_id_is_wrong);
    }

    @Override
    public void setAmountIsSmallThanRegulationError(int minDepositAmount) {
        models.get(3).isError = true;
        models.get(3).errorSTr = getString(R.string.amount_must_greater_than) + String.valueOf(minDepositAmount);
    }
}
