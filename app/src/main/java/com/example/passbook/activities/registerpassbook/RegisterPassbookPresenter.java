package com.example.passbook.activities.registerpassbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.passbook.activities.base.FormPresenter;
import com.example.passbook.daos.PassBookDAO;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.services.AppDatabase;

public class RegisterPassbookPresenter extends FormPresenter implements RegisterPassbookContract.Presenter {
    private RegisterPassbookContract.View view;

    public RegisterPassbookPresenter(RegisterPassbookContract.View anotherView) {
        super((AppCompatActivity) anotherView);
        this.view = anotherView;
    }

    @Override
    public void handleSubmit(Object... objects) {
        Customer customer = (Customer) objects[0];
        PassBook passBook = (PassBook) objects[1];

        if(manualCheck(passBook)) {
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

            view.handleSuccess();
        } else {
            view.handleFailed();
        }
    }

    private boolean manualCheck(PassBook passBook) {
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
