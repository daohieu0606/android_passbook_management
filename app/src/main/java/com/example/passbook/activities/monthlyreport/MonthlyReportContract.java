package com.example.passbook.activities.monthlyreport;

import com.example.passbook.activities.base.BaseContract;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.models.MainFuncModel;

import java.util.Date;
import java.util.List;

public interface MonthlyReportContract {
    interface View extends BaseContract.View {
        void setDataToView();
    }

    interface Presenter extends BaseContract.Presenter {
        void initTableViewData(Date startMonth, Date endMonth, PassBookType passBookType);
    }
}
