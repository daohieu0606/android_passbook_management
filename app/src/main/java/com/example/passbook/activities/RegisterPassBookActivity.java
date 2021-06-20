package com.example.passbook.activities;

import android.text.InputType;

import com.example.passbook.R;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.daos.PassBookDAO;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.InfinitePassBook;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.SixMonthPassBook;
import com.example.passbook.data.entitys.ThreeMonthPassBook;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.enums.PassbookState;
import com.example.passbook.data.models.DateTimeModel;
import com.example.passbook.data.models.SpinnerModel;
import com.example.passbook.data.models.TextFieldModel;
import com.example.passbook.utils.Constant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegisterPassBookActivity extends FormHaveSubmitButtonActivity {

    public RegisterPassBookActivity() {
        title = getString(R.string.register_passbook);
    }

    @Override
    protected void initModelAndAdapter() {
        models = new ArrayList<>();

        List<String> passBookTypes = getPassbookType();

        models.add(new TextFieldModel(getString(R.string.passbook_id), "", "", InputType.TYPE_CLASS_NUMBER));
        models.add(new SpinnerModel(Constant.PASSBOOK_TYPE, null, "", passBookTypes));
        models.add(new TextFieldModel(getString(R.string.customer_name), "", "", InputType.TYPE_CLASS_TEXT));
        models.add(new TextFieldModel(getString(R.string.identify_number), "", "", InputType.TYPE_CLASS_TEXT));
        models.add(new TextFieldModel(getString(R.string.address), "", "", InputType.TYPE_CLASS_TEXT));
        models.add(new DateTimeModel(getString(R.string.register_date), new Date(), ""));
        models.add(new TextFieldModel(getString(R.string.amount), "", "", InputType.TYPE_CLASS_NUMBER));

        adapter = new FormAdapter(this, models);
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

    @Override
    protected void HandleSubmit() {
        resetValid();

        if(isValidData()) {
            if(manualCheck()) {
                Customer customer = getCustomer();
                PassBook passBook = getPassBook();

                passBook.customerId = customer.Id;

                PassBookDAO passBookDAO = appDatabase.passBookDAO();
                passBookDAO.insertItem(passBook);

                //TODO: show dialog
                this.finish();
            }
        }

        adapter.notifyDataSetChanged();
    }

    private boolean manualCheck() {
        boolean result = true;

        PassBook passBook = appDatabase.passBookDAO().getItem(Integer.valueOf((String) models.get(0).value));

        if (passBook != null) {
            models.get(0).isError = true;
            models.get(0).errorSTr = getString(R.string.passbook_id_is_existed);
            result = false;
        }

        BankRegulation bankRegulation = appDatabase.bankRegulationDAO().getItem(1);
        int minDepositAmount = bankRegulation != null? bankRegulation.minDepositAmount: 100000;

        if(Integer.valueOf((String) models.get(6).value) < minDepositAmount) {
            models.get(6).isError = true;
            models.get(6).errorSTr = getString(R.string.amount_must_greater_than) + String.valueOf(minDepositAmount);
            result = false;
        }

        return result;
    }

    private Customer getCustomer() {
        Customer customer = null;
        customer = appDatabase.customerDAO().getCustomerByIdentifyNumber((String) models.get(3).value);

        if (customer == null) {
            customer = new Customer();
            customer.fullName = (String) models.get(2).value;
            customer.identifyNumber = (String) models.get(3).value;
            customer.address = (String) models.get(4).value;

            customer.Id = (int) appDatabase.customerDAO().insertItem(customer);
        } else {
            //do nothing
        }

        return customer;
    }

    private PassBook getPassBook() {
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

        passBook.passbookState = PassbookState.OPENED;
        passBook.creationDate = (Date) models.get(5).value;
        passBook.amount = Integer.valueOf((String) models.get(6).value);

        return passBook;
    }
}
