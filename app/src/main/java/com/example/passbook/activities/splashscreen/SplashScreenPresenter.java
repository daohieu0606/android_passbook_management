package com.example.passbook.activities.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passbook.R;
import com.example.passbook.activities.base.BasePresenter;
import com.example.passbook.data.HardCode;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.PassBookRegulation;
import com.example.passbook.data.entitys.TransactionForm;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.enums.TransactionFormType;
import com.example.passbook.utils.Utils;

public class SplashScreenPresenter extends BasePresenter implements SplashScreenContract.Presenter {
    private SplashScreenContract.View view;

    public SplashScreenPresenter(SplashScreenContract.View view) {
        super(view);

        this.view = view;
    }

    public void initPassbookRegulation() {
        if (appDatabase.passBookRegulationDAO().getItems().size() < 3) {
            PassBookRegulation threeMonthsRegulation = new PassBookRegulation();
            threeMonthsRegulation.passBookType = PassBookType.THREE_MONTH;
            threeMonthsRegulation.creationRegulationDateTime = Utils.parseDate("2020-01-01");
            threeMonthsRegulation.term = 3 * 30;
            threeMonthsRegulation.Id = 1;
            threeMonthsRegulation.interestRate = 0.005f;

            PassBookRegulation sixMonthsRegulation = new PassBookRegulation();
            sixMonthsRegulation.passBookType = PassBookType.SIX_MONTH;
            sixMonthsRegulation.creationRegulationDateTime = Utils.parseDate("2020-01-01");
            sixMonthsRegulation.term = 6 * 30;
            sixMonthsRegulation.Id = 2;
            sixMonthsRegulation.interestRate = 0.0055f;

            PassBookRegulation infiniteRegulation = new PassBookRegulation();
            infiniteRegulation.passBookType = PassBookType.INFINITE;
            infiniteRegulation.creationRegulationDateTime = Utils.parseDate("2020-01-01");
            infiniteRegulation.term = 15;
            infiniteRegulation.Id = 3;
            infiniteRegulation.interestRate = 0.0015f;

            appDatabase.passBookRegulationDAO().insertItem(threeMonthsRegulation);
            appDatabase.passBookRegulationDAO().insertItem(sixMonthsRegulation);
            appDatabase.passBookRegulationDAO().insertItem(infiniteRegulation);
        }

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initBankRegulation() {
        if(appDatabase.bankRegulationDAO().getItems().size() == 0) {
            BankRegulation bankRegulation = new BankRegulation();
            bankRegulation.existedPassBookTypes = PassBookType.THREE_MONTH.getValue()
                    | PassBookType.SIX_MONTH.getValue()
                    | PassBookType.INFINITE.getValue();
            bankRegulation.minDepositAmount = 100000;
            bankRegulation.Id = 1;

            appDatabase.bankRegulationDAO().insertItem(bankRegulation);
        }

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initData() {
        for (Customer customer:
                HardCode.HardCodeCustomer.getCustomers()) {
            appDatabase.customerDAO().insertItem(customer);
        }

        for (PassBook passBook :
                HardCode.HardCodePassbook.getPassBooks()) {
            appDatabase.passBookDAO().insertItem(passBook);
        }

        for (TransactionForm transactionForm :
                HardCode.HardCodeTransactionForm.getTransactionForms()) {
            appDatabase.transactionFormDAO().insertItem(transactionForm);

            PassBook passBook = appDatabase.passBookDAO().getItem(transactionForm.passBookId);

            if(passBook != null) {
                if(transactionForm.transactionFormType == TransactionFormType.DEPOSIT) {
                    passBook.amount += transactionForm.amount;
                } else {
                    passBook.amount -= transactionForm.amount;
                }

                appDatabase.passBookDAO().updateOrInsertItem(passBook);
            }
        }

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadingData() {
        new Thread( new Runnable() {
            @Override public void run() {
                initBankRegulation();
                view.setMessage(((AppCompatActivity)view).getString(R.string.loading_regulation));
                view.setLoadingProgress(33);

                initPassbookRegulation();
                view.setMessage(((AppCompatActivity)view).getString(R.string.loading_passbook_regulation));
                view.setLoadingProgress(66);

                initData();
                view.setMessage(((AppCompatActivity)view).getString(R.string.loading_passbook_records));
                view.setLoadingProgress(99);

                try {
                    view.setMessage(((AppCompatActivity)view).getString(R.string.finished));
                    view.setLoadingProgress(100);
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                view.moveToMainActivity();
            }
        } ).start();
    }
}
