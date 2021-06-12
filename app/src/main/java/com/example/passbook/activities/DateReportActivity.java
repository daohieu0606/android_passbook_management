package com.example.passbook.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.room.Room;

import com.example.passbook.R;
import com.example.passbook.converters.DateConverter;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.services.AppDatabase;
import com.example.passbook.utils.Constant;
import com.example.passbook.utils.Utils;
import com.levitnudi.legacytableview.LegacyTableView;

import java.util.Date;
import java.util.List;

public class DateReportActivity extends BaseActivity {
    private LegacyTableView tbDateReport;
    private TextView txtDateValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        containerLayout = R.layout.activity_date_report;
        title = Constant.DATE_REPORT;
        super.onCreate(savedInstanceState);

        initView();
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

            txtDateValue.setText(Utils.dataToString(dateToGetReport));

            List<PassBook> passBooksByDate = getPassbooks(dateToGetReport);

            initTableViewData(passBooksByDate);
        } else {
            finish();
        }
    }

    private void initTableViewData(List<PassBook> passBooksByDate) {
        LegacyTableView.insertLegacyTitle(Constant.ID,
                Constant.CREATION_DATE,
                Constant.PASSBOOK_TYPE,
                Constant.AMOUNT);

        for (PassBook passBook :
                passBooksByDate) {
            LegacyTableView.insertLegacyContent(String.valueOf(passBook.Id));
            LegacyTableView.insertLegacyContent(Utils.dataToString(passBook.creationDate));
            LegacyTableView.insertLegacyContent(passBook.passBookType.getText());
            LegacyTableView.insertLegacyContent(String.valueOf(passBook.amount));
        }

        tbDateReport.setTitle(LegacyTableView.readLegacyTitle());
        tbDateReport.setContent(LegacyTableView.readLegacyContent());

        tbDateReport.setTablePadding(7);

        tbDateReport.setZoomEnabled(true);
        tbDateReport.setShowZoomControls(true);

        tbDateReport.build();
    }

    private List<PassBook> getPassbooks(Date dateToGetPassbooks) {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        Date startDate = Utils.getStartDate(dateToGetPassbooks);
        Date endDate = Utils.getEndDate(dateToGetPassbooks);

        long from = DateConverter.dateToTimestamp(startDate);
        long to = DateConverter.dateToTimestamp(endDate);

        List<PassBook> passBooksByDate = db.passBookDAO().
                getPassBooksByDate(from, to);

        return  passBooksByDate;
    }
}
