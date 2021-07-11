package com.example.passbook.activities.monthlyreport;

import com.example.passbook.activities.base.BasePresenter;
import com.example.passbook.converters.DateConverter;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.utils.Utils;
import com.levitnudi.legacytableview.LegacyTableView;

import java.util.Date;
import java.util.List;

public class MonthlyReportPresenter extends BasePresenter implements MonthlyReportContract.Presenter {
    private MonthlyReportContract.View view;

    public MonthlyReportPresenter(MonthlyReportContract.View view) {
        super(view);

        this.view = view;
    }

    @Override
    public void initTableViewData(Date startMonth, Date endMonth, PassBookType passBookType) {
        Date currentDay = startMonth;
        int ordinalNumber = 1;

        while (!currentDay.after(endMonth)) {
            Date startDate = Utils.getStartDate(currentDay);
            Date endDate = Utils.getEndDate(currentDay);
            long from = DateConverter.dateToTimestamp(startDate);
            long to = DateConverter.dateToTimestamp(endDate);
            int numOfOpenedPassbook = 0;
            int numOfClosedPassbook = 0;
            int difference = 0;

            List<PassBook> tempPassbooks = appDatabase.passBookDAO().getItems();

            List<PassBook> passBooks = appDatabase
                    .passBookDAO()
                    .getPassBooksByDateAndType(from, to, passBookType);

            for (PassBook passBook:
                    passBooks) {
                switch (passBook.passbookState) {
                    case CLOSED:
                        numOfClosedPassbook += 1;
                        break;

                    case OPENED:
                        numOfOpenedPassbook += 1;
                        break;
                }
            }

            if(passBooks.size() > 0) {
                difference = numOfOpenedPassbook - numOfClosedPassbook;

                LegacyTableView.insertLegacyContent(String.valueOf(ordinalNumber));
                LegacyTableView.insertLegacyContent(Utils.dateToString(currentDay));
                LegacyTableView.insertLegacyContent(String.valueOf(numOfOpenedPassbook));
                LegacyTableView.insertLegacyContent(String.valueOf(numOfClosedPassbook));
                LegacyTableView.insertLegacyContent(String.valueOf(difference));

                ordinalNumber +=1;
            }

            currentDay = Utils.getNextDate(currentDay);
        }

        view.setDataToView();
    }
}
