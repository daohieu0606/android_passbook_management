package com.example.passbook.activities;

import android.content.Intent;

import com.example.passbook.R;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.converters.DateConverter;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.models.DateTimeModel;
import com.example.passbook.data.models.SpinnerModel;
import com.example.passbook.utils.Constant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelectMonthToGetReportActivity extends FormHaveSubmitButtonActivity {
    @Override
    protected void initModelAndAdapter() {
        List<String> passBookTypes = new ArrayList<>();
        passBookTypes.add(PassBookType.THREE_MONTH.getText());
        passBookTypes.add(PassBookType.SIX_MONTH.getText());
        passBookTypes.add(PassBookType.INFINITE.getText());

        models = new ArrayList<>();
        models.add(new SpinnerModel(Constant.PASSBOOK_TYPE, null, "", passBookTypes));
        //TODO: pick up only month and year
        models.add(new DateTimeModel(getString(R.string.pick_up_date), new Date(), ""));

        adapter = new FormAdapter(this, models);
    }

    @Override
    protected void HandleSubmit() {
        resetValid();

        if(isValidData()) {
            PassBookType passBookType = PassBookType.fromString((String) models.get(0).value);
            Date date = (Date) models.get(1).value;
            long dataValue = DateConverter.dateToTimestamp(date);

            Intent intent = new Intent(this, MonthlyReportActivity.class);
            intent.putExtra(Constant.DATE_TO_GET_REPORT, dataValue);
            intent.putExtra(Constant.PASSBOOK_TYPE, passBookType.getText());

            startActivity(intent);
        }
    }
}
