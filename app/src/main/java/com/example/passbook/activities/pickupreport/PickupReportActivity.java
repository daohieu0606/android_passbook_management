package com.example.passbook.activities.pickupreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.passbook.R;
import com.example.passbook.activities.selectmonthtogetreport.SelectMonthToGetReportActivity;
import com.example.passbook.activities.base.TabBarActivity;
import com.example.passbook.activities.datereport.DateReportActivity;
import com.example.passbook.converters.DateConverter;
import com.example.passbook.customviews.CustomButton;
import com.example.passbook.utils.Constant;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PickupReportActivity extends TabBarActivity
        implements View.OnClickListener, PickupReportContract.View {
    private CustomButton btnReportByDate;
    private CustomButton btnReportByMonth;

    private PickupReportContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        containerLayout = R.layout.activity_pickup_report;
        title = getString(R.string.report);
        super.onCreate(savedInstanceState);

        presenter = new PickupReportPresenter(this);
        init();
        Date date = new Date();
    }

    private void init() {
        btnReportByDate = findViewById(R.id.btnReportByDate);
        btnReportByMonth = findViewById(R.id.btnReportByMonth);

        btnReportByDate.setOnClickListener(this);
        btnReportByMonth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReportByDate:
                getDateReportActivity();
                break;

            case R.id.btnReportByMonth:
                Intent intentToMonthReportActivity = new Intent(
                        this,
                        SelectMonthToGetReportActivity.class);
                startActivity(intentToMonthReportActivity);

                break;

            default:
                break;
        }
    }

    private void getDateReportActivity() {
        MaterialDatePicker datePicker = MaterialDatePicker
                .Builder
                .datePicker()
                .setTitleText(getString(R.string.select_date))
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();
        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(Constant.UTC));
                calendar.setTimeInMillis((Long) selection);

                Date value = calendar.getTime();
                moveToDateReportActivity(value);
            }
        });

        datePicker.show(this.getSupportFragmentManager(), "tag");
    }

    private void moveToDateReportActivity(Date date) {
        Intent intent = new Intent(this, DateReportActivity.class);
        intent.putExtra(Constant.DATE_TO_GET_REPORT, DateConverter.dateToTimestamp(date));
        startActivity(intent);
    }
}
