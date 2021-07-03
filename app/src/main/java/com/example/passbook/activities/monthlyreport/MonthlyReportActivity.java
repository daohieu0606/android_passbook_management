package com.example.passbook.activities.monthlyreport;

import android.os.Bundle;
import android.widget.TextView;

import com.example.passbook.R;
import com.example.passbook.activities.base.TabBarActivity;
import com.example.passbook.converters.DateConverter;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.utils.Constant;
import com.example.passbook.utils.Utils;
import com.levitnudi.legacytableview.LegacyTableView;

import java.util.Date;

public class MonthlyReportActivity extends TabBarActivity implements MonthlyReportContract.View {
    private TextView txtPassBookType;
    private TextView txtMonth;
    private LegacyTableView tbPassbook;
    private Date startMonth;
    private Date endMonth;
    private PassBookType passBookType;

    private MonthlyReportContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        containerLayout = R.layout.activity_monthly_report;
        title = getString(R.string.month_report);
        super.onCreate(savedInstanceState);

        presenter = new MonthlyReportPresenter(this);

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
        initTable();
    }

    private void initView() {
        txtPassBookType = findViewById(R.id.txtPassBookType);
        txtMonth = findViewById(R.id.txtMonth);
        tbPassbook = findViewById(R.id.tbPassbook);
    }

    private void initData() {
        String passBookTypeStr = getIntent().getStringExtra(Constant.PASSBOOK_TYPE);
        passBookType = PassBookType.fromString(passBookTypeStr);
        long monthToGetReportLong = getIntent().getLongExtra(Constant.DATE_TO_GET_REPORT, -1);

        if(passBookType == null || monthToGetReportLong == -1) {
            finish();
        } else {
            Date monthToGetReport = DateConverter.fromTimestamp(monthToGetReportLong);
            startMonth = Utils.getStartMonth(monthToGetReport);
            endMonth = Utils.getEndMonth(monthToGetReport);

            //TODO: refactor below codes to new func
            txtMonth.setText(Utils.dateToString(monthToGetReport));
            txtPassBookType.setText(passBookType.getText());
        }
    }

    //TODO: refactor this func to clean
    private void initTable() {
        LegacyTableView.insertLegacyTitle(
                getString(R.string.ordinal_number),
                getString(R.string.creation_date),
                getString(R.string.opened_passbook),
                getString(R.string.closed_passbook),
                getString(R.string.difference));

        presenter.initTableViewData(startMonth, endMonth, passBookType);

    }

    @Override
    public void setDataToView() {
        tbPassbook.setTitle(LegacyTableView.readLegacyTitle());
        tbPassbook.setContent(LegacyTableView.readLegacyContent());

        tbPassbook.setTablePadding(7);

        tbPassbook.setZoomEnabled(true);
        tbPassbook.setShowZoomControls(true);

        tbPassbook.build();
    }
}
