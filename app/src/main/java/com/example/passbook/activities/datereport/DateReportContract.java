package com.example.passbook.activities.datereport;

import com.example.passbook.activities.base.BaseContract;
import com.example.passbook.data.entitys.PassBook;

import java.util.Date;
import java.util.List;

public interface DateReportContract {
    interface View extends BaseContract.View {
        void setDataToView();
    }

    interface Presenter extends BaseContract.Presenter {
        List<PassBook> getPassbooks(Date dateToGetPassbooks);
        void initTableViewData(List<PassBook> passBooksByDate);
    }
}
