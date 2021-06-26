package com.example.passbook.activities.selectmonthtogetreport;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passbook.activities.form.FormContract;
import com.example.passbook.activities.form.FormPresenter;
import com.example.passbook.activities.monthlyreport.MonthlyReportActivity;
import com.example.passbook.converters.DateConverter;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.utils.Constant;

import java.util.Date;

public class SelectMonthToGetReportPresenter extends FormPresenter
        implements SelectMonthToGetReportContract.Presenter {

    private SelectMonthToGetReportContract.View view;

    public SelectMonthToGetReportPresenter(SelectMonthToGetReportContract.View view) {
        super(view);

        this.view = view;
    }

    @Override
    protected void saveData(Object... objects) {
        PassBookType passBookType = (PassBookType) objects[0];
        Date date = (Date) objects[1];

        long dataValue = DateConverter.dateToTimestamp(date);

        Intent intent = new Intent((AppCompatActivity) view, MonthlyReportActivity.class);
        intent.putExtra(Constant.DATE_TO_GET_REPORT, dataValue);
        intent.putExtra(Constant.PASSBOOK_TYPE, passBookType.getText());

        view.moveToAnotherActivity(intent);
    }

    @Override
    protected boolean manualCheck(Object... objects) {
        return true;
    }
}
