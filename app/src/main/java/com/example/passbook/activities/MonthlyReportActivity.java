package com.example.passbook.activities;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import com.example.passbook.R;
import com.example.passbook.converters.DateConverter;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.TransactionForm;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.utils.Constant;
import com.example.passbook.utils.Utils;
import com.levitnudi.legacytableview.LegacyTableView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

public class MonthlyReportActivity extends BaseActivity {
    private TextView txtPassBookType;
    private TextView txtMonth;
    private LegacyTableView tbPassbook;
    private Date startMonth;
    private Date endMonth;
    private PassBookType passBookType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        containerLayout = R.layout.activity_monthly_report;
        title = getString(R.string.month_report);
        super.onCreate(savedInstanceState);

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
            txtMonth.setText(Utils.dataToString(monthToGetReport));
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

        Date currentDay = startMonth;
        while (!currentDay.after(endMonth)) {
            Date startDate = Utils.getStartDate(currentDay);
            Date endDate = Utils.getEndDate(currentDay);
            long from = DateConverter.dateToTimestamp(startDate);
            long to = DateConverter.dateToTimestamp(endDate);
            int numOfOpenedPassbook = 0;
            int numOfClosedPassbook = 0;
            int difference = 0;
            int ordinalNumber = 1;

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
                LegacyTableView.insertLegacyContent(Utils.dataToString(currentDay));
                LegacyTableView.insertLegacyContent(String.valueOf(numOfOpenedPassbook));
                LegacyTableView.insertLegacyContent(String.valueOf(numOfClosedPassbook));
                LegacyTableView.insertLegacyContent(String.valueOf(difference));

                ordinalNumber +=1;
            }

            currentDay = Utils.getNextDate(currentDay);
        }

        tbPassbook.setTitle(LegacyTableView.readLegacyTitle());
        tbPassbook.setContent(LegacyTableView.readLegacyContent());

        tbPassbook.setTablePadding(7);

        tbPassbook.setZoomEnabled(true);
        tbPassbook.setShowZoomControls(true);

        tbPassbook.build();
    }
}
