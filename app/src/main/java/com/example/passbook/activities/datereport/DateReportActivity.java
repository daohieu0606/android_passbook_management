package com.example.passbook.activities.datereport;

import android.os.Bundle;
import android.widget.TextView;

import com.example.passbook.R;
import com.example.passbook.activities.base.TabBarActivity;
import com.example.passbook.converters.DateConverter;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.utils.Constant;
import com.example.passbook.utils.Utils;
import com.levitnudi.legacytableview.LegacyTableView;

import java.util.Date;
import java.util.List;

public class DateReportActivity extends TabBarActivity implements DateReportContract.View {
    private LegacyTableView tbDateReport;
    private TextView txtDateValue;
    private DateReportContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        containerLayout = R.layout.activity_date_report;
        title = getString(R.string.date_report);
        super.onCreate(savedInstanceState);

        initView();

        presenter = new DateReportPresenter(this);
    }

    private void initView() {
        tbDateReport = findViewById(R.id.tbDateReport);
        txtDateValue = findViewById(R.id.txtDateValue);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        Long dateToGetReportLong = getIntent().getLongExtra(Constant.DATE_TO_GET_REPORT, -1);

        if(dateToGetReportLong != -1) {
            Date dateToGetReport = DateConverter.fromTimestamp(dateToGetReportLong);

            txtDateValue.setText(Utils.dateToString(dateToGetReport));

            List<PassBook> passBooksByDate = presenter.getPassbooks(dateToGetReport);

            initTableViewData(passBooksByDate);
        } else {
            finish();
        }
    }

    private void initTableViewData(List<PassBook> passBooksByDate) {
        LegacyTableView.insertLegacyTitle(Constant.ID,
                getString(R.string.passbook_type),
                getString(R.string.deposit_total),
                getString(R.string.withdrawal_total),
                getString(R.string.difference));

        presenter.initTableViewData(passBooksByDate);
    }

    @Override
    public void setDataToView() {
        tbDateReport.setTitle(LegacyTableView.readLegacyTitle());
        tbDateReport.setContent(LegacyTableView.readLegacyContent());

        tbDateReport.setTablePadding(7);

        tbDateReport.setZoomEnabled(true);
        tbDateReport.setShowZoomControls(true);

        tbDateReport.build();
    }
}
