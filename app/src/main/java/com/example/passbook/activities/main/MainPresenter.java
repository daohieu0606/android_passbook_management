package com.example.passbook.activities.main;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passbook.activities.base.BasePresenter;
import com.example.passbook.activities.editdepositslip.EditDepositActivity;
import com.example.passbook.activities.editwithdrawslip.EditWithdrawalActivity;
import com.example.passbook.activities.changeregulation.ChangeRegulationTypeActivity;
import com.example.passbook.activities.pickupreport.PickupReportActivity;
import com.example.passbook.activities.registerpassbook.RegisterPassBookActivity;
import com.example.passbook.activities.searchpassbook.SearchPassBookActivity;
import com.example.passbook.data.HardCode;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.PassBookRegulation;
import com.example.passbook.data.entitys.TransactionForm;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.models.MainFuncModel;
import com.example.passbook.utils.Utils;

public class MainPresenter extends BasePresenter implements MainContract.Presenter {
    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        super(view);

        this.view = view;
    }

    @Override
    public void intData() {
        initBankRegulation();
        intPassbookRegulation();
        hardcode(); //TODO: remove hard code
    }

    private void intPassbookRegulation() {
        if (appDatabase.passBookRegulationDAO().getItems().size() < 3) {
            PassBookRegulation threeMonthsRegulation = new PassBookRegulation();
            threeMonthsRegulation.passBookType = PassBookType.THREE_MONTH;
            threeMonthsRegulation.creationDateTime = Utils.parseDate("2020-01-01");
            threeMonthsRegulation.term = 3 * 30;
            threeMonthsRegulation.Id = 1;
            threeMonthsRegulation.interestRate = 0.005f;

            PassBookRegulation sixMonthsRegulation = new PassBookRegulation();
            sixMonthsRegulation.passBookType = PassBookType.SIX_MONTH;
            sixMonthsRegulation.creationDateTime = Utils.parseDate("2020-01-01");
            sixMonthsRegulation.term = 6 * 30;
            sixMonthsRegulation.Id = 2;
            sixMonthsRegulation.interestRate = 0.0055f;

            PassBookRegulation infiniteRegulation = new PassBookRegulation();
            infiniteRegulation.passBookType = PassBookType.INFINITE;
            infiniteRegulation.creationDateTime = Utils.parseDate("2020-01-01");
            infiniteRegulation.term = 0;
            infiniteRegulation.Id = 3;
            infiniteRegulation.interestRate = 0.0015f;

            appDatabase.passBookRegulationDAO().insertItem(threeMonthsRegulation);
            appDatabase.passBookRegulationDAO().insertItem(sixMonthsRegulation);
            appDatabase.passBookRegulationDAO().insertItem(infiniteRegulation);
        }
    }

    private void hardcode() {
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
        }
    }

    private void initBankRegulation() {
        if(appDatabase.bankRegulationDAO().getItems().size() == 0) {
            BankRegulation bankRegulation = new BankRegulation();
            bankRegulation.existedPassBookTypes = PassBookType.THREE_MONTH.getValue()
                    | PassBookType.SIX_MONTH.getValue()
                    | PassBookType.INFINITE.getValue();
            bankRegulation.minDepositAmount = 100000;
            bankRegulation.Id = 1;

            appDatabase.bankRegulationDAO().insertItem(bankRegulation);
        }
    }

    @Override
    public void handleItemClick(MainFuncModel item) {
        Intent intent = null;

        switch (item.applicationFunction) {
            case REGISTER_PASSBOOK:
                intent = new Intent((AppCompatActivity)view, RegisterPassBookActivity.class);
                break;

            case GET_DEPOSIT_SLIP:
                intent = new Intent((AppCompatActivity)view, EditDepositActivity.class);
                break;

            case GET_WITHDRAWAL_SLIP:
                intent = new Intent((AppCompatActivity)view, EditWithdrawalActivity.class);
                break;

            case SEARCH_PASSBOOKS:
                intent = new Intent((AppCompatActivity)view, SearchPassBookActivity.class);
                break;

            case REPORT:
                intent = new Intent((AppCompatActivity)view, PickupReportActivity.class);
                break;

            case CHANGE_REGULATIONS:
                intent = new Intent((AppCompatActivity)view, ChangeRegulationTypeActivity.class);
                break;

            default:

        }

        view.moveToAnotherActivity(intent);
    }
}
