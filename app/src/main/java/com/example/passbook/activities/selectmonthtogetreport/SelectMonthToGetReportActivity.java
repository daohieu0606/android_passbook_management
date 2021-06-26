package com.example.passbook.activities.selectmonthtogetreport;

import android.os.Bundle;

import com.example.passbook.R;
import com.example.passbook.activities.form.FormHaveSubmitButtonActivity;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.models.DateTimeModel;
import com.example.passbook.data.models.SpinnerModel;
import com.example.passbook.utils.Constant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelectMonthToGetReportActivity extends FormHaveSubmitButtonActivity
        implements SelectMonthToGetReportContract.View {

    private SelectMonthToGetReportContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SelectMonthToGetReportPresenter(this);
    }

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
    protected void getDataFromViewAndCallPresenterHandle() {
        PassBookType passBookType = PassBookType.fromString((String) models.get(0).value);
        Date date = (Date) models.get(1).value;

        presenter.handleSubmit(passBookType, date);
    }
}
