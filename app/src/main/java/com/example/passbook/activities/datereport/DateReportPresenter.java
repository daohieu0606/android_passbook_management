package com.example.passbook.activities.datereport;

import com.example.passbook.activities.base.BasePresenter;
import com.example.passbook.converters.DateConverter;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.TransactionForm;
import com.example.passbook.utils.Utils;
import com.levitnudi.legacytableview.LegacyTableView;

import java.util.Date;
import java.util.List;

public class DateReportPresenter extends BasePresenter implements DateReportContract.Presenter{
    private DateReportContract.View view;

    public DateReportPresenter(DateReportContract.View view) {
        super(view);

        this.view = view;
    }

    @Override
    public List<PassBook> getPassbooks(Date dateToGetPassbooks) {
        Date startDate = Utils.getStartDate(dateToGetPassbooks);
        Date endDate = Utils.getEndDate(dateToGetPassbooks);

        long from = DateConverter.dateToTimestamp(startDate);
        long to = DateConverter.dateToTimestamp(endDate);

        List<PassBook> passBooksByDate = appDatabase.passBookDAO().
                getPassBooksByDate(from, to);

        return  passBooksByDate;
    }

    @Override
    public void initTableViewData(List<PassBook> passBooksByDate) {
        for (PassBook passBook :
                passBooksByDate) {
            List<TransactionForm> transactionForms = appDatabase
                    .transactionFormDAO()
                    .getItemsByPassbookIdAndCustomerId(passBook.Id, passBook.customerId);

            int depositTotal = 0;
            int withdrawTotal = 0;
            int difference = 0;

            for (TransactionForm transactionForm :
                    transactionForms) {
                switch (transactionForm.transactionFormType) {
                    case DEPOSIT:
                        depositTotal += transactionForm.amount;
                        break;

                    case WITHDRAWAL:
                        withdrawTotal += transactionForm.amount;
                        break;
                }
            }

            difference = depositTotal - withdrawTotal;

            LegacyTableView.insertLegacyContent(String.valueOf(passBook.Id));
            LegacyTableView.insertLegacyContent(passBook.passBookType.getText());
            LegacyTableView.insertLegacyContent(String.valueOf(depositTotal));
            LegacyTableView.insertLegacyContent(String.valueOf(withdrawTotal));
            LegacyTableView.insertLegacyContent(String.valueOf(difference));
        }

        /*for(int i = 0; i< passBooksByDate.size(); i++) {

        }*/

        view.setDataToView();
    }
}
