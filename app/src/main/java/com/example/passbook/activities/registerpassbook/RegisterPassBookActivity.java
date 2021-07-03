package com.example.passbook.activities.registerpassbook;

import android.os.Bundle;
import android.text.InputType;

import com.example.passbook.R;
import com.example.passbook.activities.form.FormHaveSubmitButtonActivity;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.InfinitePassBook;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.SixMonthPassBook;
import com.example.passbook.data.entitys.ThreeMonthPassBook;
import com.example.passbook.data.enums.DatePickerType;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.enums.PassbookState;
import com.example.passbook.data.models.DateTimeModel;
import com.example.passbook.data.models.SpinnerModel;
import com.example.passbook.data.models.TextFieldModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegisterPassBookActivity extends FormHaveSubmitButtonActivity implements RegisterPassbookContract.View {
    private RegisterPassbookContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        presenter = new RegisterPassbookPresenter(this);

        super.onCreate(savedInstanceState);
        title = getString(R.string.register_passbook);
    }

    @Override
    protected void initModelAndAdapter() {
        models = new ArrayList<>();

        List<String> passBookTypes = getPassbookType();

        int nextPassbookId = presenter.getNextPassbookId();

        models.add(new TextFieldModel(getString(R.string.passbook_id), String.valueOf(nextPassbookId), "", InputType.TYPE_CLASS_NUMBER));
        models.add(new SpinnerModel(getString(R.string.passbook_type), null, "", passBookTypes));
        models.add(new TextFieldModel(getString(R.string.customer_name), "", "", InputType.TYPE_CLASS_TEXT));
        models.add(new TextFieldModel(getString(R.string.identify_number), "", "", InputType.TYPE_CLASS_TEXT));
        models.add(new TextFieldModel(getString(R.string.address), "", "", InputType.TYPE_CLASS_TEXT));
        models.add(new DateTimeModel(getString(R.string.register_date), new Date(), "", DatePickerType.NORMAL));
        models.add(new TextFieldModel(getString(R.string.amount), "", "", InputType.TYPE_CLASS_NUMBER));

        adapter = new FormAdapter(this, models, lst_input);
    }

    @Override
    protected void getDataFromViewAndCallPresenterHandle() {
        Customer customer = getCustomerFromView();
        PassBook passBook = getPassBookFromView();

        presenter.handleSubmit(customer, passBook);
    }

    private List<String> getPassbookType() {
        List<String> passBookTypes = new ArrayList<>();
        BankRegulation bankRegulation = appDatabase.bankRegulationDAO().getItem(1);

        if(bankRegulation == null) {
            //TODO: remove finish() and call function init bank regulation
            finish();
        } else {
            if((bankRegulation.existedPassBookTypes & PassBookType.THREE_MONTH.getValue()) != 0) {
                passBookTypes.add(PassBookType.THREE_MONTH.getText());
            }
            if((bankRegulation.existedPassBookTypes & PassBookType.SIX_MONTH.getValue()) != 0) {
                passBookTypes.add(PassBookType.SIX_MONTH.getText());
            }
            if((bankRegulation.existedPassBookTypes & PassBookType.INFINITE.getValue()) != 0) {
                passBookTypes.add(PassBookType.INFINITE.getText());
            }
        }

        return passBookTypes;
    }

    private Customer getCustomerFromView() {
        Customer customer = new Customer();

        customer.fullName = (String) models.get(2).value;
        customer.identifyNumber = (String) models.get(3).value;
        customer.address = (String) models.get(4).value;

        return customer;
    }

    private PassBook getPassBookFromView() {
        PassBook passBook = null;
        PassBookType passBookType = PassBookType.fromString((String) models.get(1).value);

        switch (passBookType) {
            case THREE_MONTH:
                passBook = new ThreeMonthPassBook();
                break;

            case SIX_MONTH:
                passBook = new SixMonthPassBook();
                break;

            case INFINITE:
            default:
                passBook = new InfinitePassBook();
        }

        passBook.Id = Integer.valueOf((String) models.get(0).value);
        passBook.passbookState = PassbookState.OPENED;
        passBook.creationDate = (Date) models.get(5).value;
        passBook.amount = Integer.valueOf((String) models.get(6).value);

        return passBook;
    }

    @Override
    public void setPassBookIsExistedError() {
        models.get(0).isError = true;
        models.get(0).errorSTr = getString(R.string.passbook_id_was_existed);
    }

    @Override
    public void setAmountIsSmallThanRegulationError(int minDepositAmount) {
        models.get(6).isError = true;
        models.get(6).errorSTr = getString(R.string.amount_must_greater_then) + String.valueOf(minDepositAmount);
    }
}
