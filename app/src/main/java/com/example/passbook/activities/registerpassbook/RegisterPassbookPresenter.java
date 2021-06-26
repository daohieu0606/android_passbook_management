package com.example.passbook.activities.registerpassbook;

import com.example.passbook.activities.form.FormPresenter;
import com.example.passbook.daos.PassBookDAO;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.PassBook;

public class RegisterPassbookPresenter extends FormPresenter implements RegisterPassbookContract.Presenter {
    private RegisterPassbookContract.View view;

    public RegisterPassbookPresenter(RegisterPassbookContract.View anotherView) {
        super(anotherView);
        this.view = anotherView;
    }

    @Override
    protected void saveData(Object... objects) {
        Customer customer = (Customer) objects[0];
        PassBook passBook = (PassBook) objects[1];

        Customer existedCustomer = appDatabase
                .customerDAO()
                .getCustomerByIdentifyNumberAndName(customer.identifyNumber, customer.fullName);

        if(existedCustomer == null) {
            int id = (int) appDatabase.customerDAO().insertItem(customer);

            customer.Id = id;
        } else {
            customer.Id = existedCustomer.Id;
        }

        passBook.customerId = customer.Id;
        PassBookDAO passBookDAO = appDatabase.passBookDAO();
        passBookDAO.insertItem(passBook);
    }

    @Override
    protected boolean manualCheck(Object... objects) {
        Customer customer = (Customer) objects[0];
        PassBook passBook = (PassBook) objects[1];

        boolean result = true;

        if (appDatabase.passBookDAO().getItem(passBook.Id) != null) {
            view.setPassBookIsExistedError();
            result = false;
        }

        BankRegulation bankRegulation = appDatabase.bankRegulationDAO().getItem(1);
        int minDepositAmount = bankRegulation != null? bankRegulation.minDepositAmount: 100000;

        if(passBook.amount < minDepositAmount) {
            view.setAmountIsSmallThanRegulationError(minDepositAmount);
            result = false;
        }

        return result;
    }
}
