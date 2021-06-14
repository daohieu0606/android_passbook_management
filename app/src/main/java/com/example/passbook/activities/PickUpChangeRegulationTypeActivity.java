package com.example.passbook.activities;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.room.Room;

import com.example.passbook.R;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.customviews.CustomDialog;
import com.example.passbook.customviews.DialogHaveListView;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.PassBookRegulation;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.models.BaseFormModel;
import com.example.passbook.data.models.SpinnerModel;
import com.example.passbook.data.models.TextFieldModel;
import com.example.passbook.intefaces.OnDialogButtonClick;
import com.example.passbook.services.AppDatabase;
import com.example.passbook.utils.Constant;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PickUpChangeRegulationTypeActivity extends BaseActivity implements View.OnClickListener {
    private Button btnNumOfType;
    private Button btnMinDeposit;
    private Button btnMinTime;
    private Button btnInterestRate;

    private DialogHaveListView dialog;
    private List<BaseFormModel> models;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        title = Constant.REGULATIONS;
        containerLayout = R.layout.activity_pickup_regulation_change_type;

        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        btnNumOfType = findViewById(R.id.btnNumOfType);
        btnMinDeposit = findViewById(R.id.btnMinDeposit);
        btnMinTime = findViewById(R.id.btnMinTime);
        btnInterestRate = findViewById(R.id.btnInterestRate);

        btnNumOfType.setOnClickListener(this::onClick);
        btnMinDeposit.setOnClickListener(this::onClick);
        btnMinTime.setOnClickListener(this::onClick);
        btnInterestRate.setOnClickListener(this::onClick);
    }

    @Override
    protected void onStart() {
        super.onStart();

        models = new ArrayList<>();
        dialog = new DialogHaveListView(this, models);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    @Override
    public void onClick(View v) {

        dialog = new DialogHaveListView(this, models);

        switch (v.getId()) {
            case R.id.btnNumOfType:
                changeNumOfPassbookTypes();
                break;

            case R.id.btnMinDeposit:
                changeMinDepositAmount();
                break;

            case R.id.btnMinTime:
                changeMinDepositTime();
                break;

            case R.id.btnInterestRate:
                changeInterestRate();
        }
    }

    private void changeInterestRate() {
        models.clear();

        List<String> passBookTypes = new ArrayList<>();
        passBookTypes.add(PassBookType.THREE_MONTH.getText());
        passBookTypes.add(PassBookType.SIX_MONTH.getText());
        passBookTypes.add(PassBookType.INFINITE.getText());

        models.add(new SpinnerModel(Constant.PASSBOOK_TYPE, null, "", passBookTypes));
        models.add(new TextFieldModel(
                Constant.INTEREST_RATE,
                "",
                "",
                InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL));

        dialog.onDialogButtonClick = new OnDialogButtonClick() {
            @Override
            public void onNegativeClick() {

            }

            @Override
            public void onPositiveClick() {
                Float interestRate = Float.valueOf((String) models.get(1).value);

                if(isGreaterThanZero(interestRate)) {
                    PassBookType passBookType = PassBookType.fromString((String) models.get(0).value);

                    updateInterestRate(passBookType, interestRate);

                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                } else {
                    models.get(1).isError = true;
                    models.get(1).errorSTr = "value must be greater than zero";
                    dialog.notifyDataChanged();
                }
            }
        };

        dialog.show();
    }

    private void changeMinDepositTime() {
        models.clear();

        List<String> passBookTypes = new ArrayList<>();
        passBookTypes.add(PassBookType.THREE_MONTH.getText());
        passBookTypes.add(PassBookType.SIX_MONTH.getText());
        passBookTypes.add(PassBookType.INFINITE.getText());

        models.add(new SpinnerModel(Constant.PASSBOOK_TYPE, null, "", passBookTypes));
        models.add(new TextFieldModel(Constant.MIN_DEPOSIT_TIME, "", "", InputType.TYPE_CLASS_NUMBER));

        dialog.onDialogButtonClick = new OnDialogButtonClick() {
            @Override
            public void onNegativeClick() {

            }

            @Override
            public void onPositiveClick() {
                Integer minTime = Integer.valueOf((String) models.get(1).value);

                if(isGreaterThanZero(minTime)) {
                    PassBookType passBookType = PassBookType.fromString((String) models.get(0).value);

                    updateMinTime(passBookType, minTime);

                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                } else {
                    models.get(1).isError = true;
                    models.get(1).errorSTr = "value must be greater than zero";
                    dialog.notifyDataChanged();
                }
            }
        };

        dialog.show();
    }

    private void changeMinDepositAmount() {
        models.clear();
        models.add(new TextFieldModel(Constant.MIN_DEPOSIT_AMOUNT, "", "", InputType.TYPE_CLASS_NUMBER));

        dialog.onDialogButtonClick = new OnDialogButtonClick() {
            @Override
            public void onNegativeClick() {

            }

            @Override
            public void onPositiveClick() {
                Integer minDeposit = Integer.valueOf((String) models.get(0).value);

                if(isGreaterThanZero(minDeposit)) {
                    updateMinDeposit(Integer.valueOf((String) models.get(0).value));

                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                } else {
                    models.get(0).isError = true;
                    models.get(0).errorSTr = "value must be greater than zero";
                    dialog.notifyDataChanged();
                }
            }
        };

        dialog.show();
    }

    private void changeNumOfPassbookTypes() {
        CustomDialog customDialog = new CustomDialog(this, R.layout.body_switch_passbook_types);

        SwitchMaterial swThreeMonthsType;
        SwitchMaterial swSixMonthsType;
        SwitchMaterial swInfiniteType;

        swThreeMonthsType = customDialog.getBody().findViewById(R.id.swThreeMonthsType);
        swSixMonthsType = customDialog.getBody().findViewById(R.id.swSixMonthsType);
        swInfiniteType = customDialog.getBody().findViewById(R.id.swInfiniteType);

        BankRegulation bankRegulation = getBankRegulation();

        if((bankRegulation.existedPassBookTypes & PassBookType.THREE_MONTH.getValue()) != 0) {
            swThreeMonthsType.setChecked(true);
        }
        if((bankRegulation.existedPassBookTypes & PassBookType.SIX_MONTH.getValue()) != 0) {
            swSixMonthsType.setChecked(true);
        }
        if((bankRegulation.existedPassBookTypes & PassBookType.INFINITE.getValue()) != 0) {
            swInfiniteType.setChecked(true);
        }

        customDialog.onDialogButtonClick = new OnDialogButtonClick() {
            @Override
            public void onNegativeClick() {

            }

            @Override
            public void onPositiveClick() {
                int result = 0;

                if(swThreeMonthsType.isChecked()) {
                    result |= 1;
                }
                if(swSixMonthsType.isChecked()) {
                    result |= 2;
                }
                if(swInfiniteType.isChecked()) {
                    result |= 4;
                }

                bankRegulation.existedPassBookTypes = result;

                appDatabase.bankRegulationDAO().updateOrInsertItem(bankRegulation);
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                customDialog.dismiss();
            }
        };

        customDialog.show();
    }

    private boolean isGreaterThanZero(Object value) {
        if (value instanceof Integer) {
            return ((Integer)value) > 0? true: false;
        } else if(value instanceof Float) {
            return ((Float)value) > 0.0f? true: false;
        }

        return false;
    }

    private void updateInterestRate(PassBookType passBookType, Float value) {
        PassBookRegulation passBookRegulation = getPassBookRegulation(passBookType);
        passBookRegulation.interestRate = value;

        db.passBookRegulationDAO().insertItem(passBookRegulation);
    }

    private void updateMinTime(PassBookType passBookType, Integer value) {
        PassBookRegulation passBookRegulation = getPassBookRegulation(passBookType);
        passBookRegulation.term = value;

        db.passBookRegulationDAO().insertItem(passBookRegulation);
    }

    private void updateMinDeposit(Integer value) {
        BankRegulation bankRegulation = getBankRegulation();

        bankRegulation.minDepositAmount = value;

        db.bankRegulationDAO().updateOrInsertItem(bankRegulation);
    }

    private PassBookRegulation getPassBookRegulation(PassBookType passBookType) {
        PassBookRegulation passBookRegulation = db.passBookRegulationDAO().getLastPassBookByType(passBookType);

        if(passBookRegulation == null) {
            passBookRegulation = new PassBookRegulation();
            passBookRegulation.interestRate = 0.005f;
            passBookRegulation.term = 30;
            passBookRegulation.passBookType = passBookType;
        }

        passBookRegulation.Id = 0;
        passBookRegulation.creationDateTime = new Date();

        return passBookRegulation;
    }

    private BankRegulation getBankRegulation() {
        BankRegulation bankRegulation = db.bankRegulationDAO().getItem(1);

        if (bankRegulation == null) {
            bankRegulation = new BankRegulation();
            bankRegulation.Id = 1;
            bankRegulation.existedPassBookTypes = PassBookType.THREE_MONTH.getValue()
                    | PassBookType.SIX_MONTH.getValue()
                    | PassBookType.INFINITE.getValue();
            bankRegulation.minDepositAmount = 100000;
        }

        return bankRegulation;
    }
}
