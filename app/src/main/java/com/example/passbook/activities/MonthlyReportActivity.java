package com.example.passbook.activities;

import android.os.Bundle;

import com.example.passbook.R;
import com.example.passbook.utils.Constant;

public class MonthlyReportActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        containerLayout = R.layout.activity_monthly_report;
        title = Constant.MONTH_REPORT;
        super.onCreate(savedInstanceState);
    }
}
