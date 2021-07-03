package com.example.passbook.activities.pickupchangeregulation;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.passbook.R;
import com.example.passbook.activities.base.TabBarActivity;
import com.example.passbook.customviews.CustomDialog;
import com.example.passbook.customviews.DialogHaveListView;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.PassBookRegulation;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.models.BaseFormModel;
import com.example.passbook.data.models.SpinnerModel;
import com.example.passbook.data.models.TextFieldModel;
import com.example.passbook.intefaces.OnDialogButtonClick;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PickUpChangeRegulationTypeActivity extends TabBarActivity
        implements View.OnClickListener, PickUpChangeRegulationTypeContract.View {
    private Button btnNumOfType;
    private Button btnMinDeposit;
    private Button btnTerm;
    private Button btnInterestRate;

    private DialogHaveListView dialog;
    private List<BaseFormModel> models;

    private PickUpChangeRegulationTypeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        title = getString(R.string.regulations);
        containerLayout = R.layout.activity_pickup_regulation_change_type;

        super.onCreate(savedInstanceState);

        presenter = new PickUpChangeRegulationTypePresenter(this);
        init();
    }

    private void init() {
        btnNumOfType = findViewById(R.id.btnNumOfType);
        btnMinDeposit = findViewById(R.id.btnMinDeposit);
        btnTerm = findViewById(R.id.btnTerm);
        btnInterestRate = findViewById(R.id.btnInterestRate);

        btnNumOfType.setOnClickListener(this::onClick);
        btnMinDeposit.setOnClickListener(this::onClick);
        btnTerm.setOnClickListener(this::onClick);
        btnInterestRate.setOnClickListener(this::onClick);
    }

    @Override
    protected void onStart() {
        super.onStart();

        models = new ArrayList<>();
        dialog = new DialogHaveListView(this, models, getString(R.string.change_regulation));
    }

    @Override
    public void onClick(View v) {

        dialog = new DialogHaveListView(this, models, getString(R.string.change_regulation));

        switch (v.getId()) {
            case R.id.btnNumOfType:
                changeNumOfPassbookTypes();
                break;

            case R.id.btnMinDeposit:
                changeMinDepositAmount();
                break;

            case R.id.btnTerm:
                changeTerm();
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

        models.add(new SpinnerModel(getResources().getString(R.string.passbook_type), null, "", passBookTypes));
        models.add(new TextFieldModel(
                getString(R.string.interest_rate),
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

                if(interestRate >= PassBookRegulation.MIN_INTEREST_RATE) {
                    PassBookType passBookType = PassBookType.fromString((String) models.get(0).value);

                    updateInterestRate(passBookType, interestRate);

                    Toast.makeText(getApplicationContext(), getString(R.string.success), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                } else {
                    models.get(1).isError = true;
                    models.get(1).errorSTr = getString(R.string.value_must_greater_the_zero);
                    dialog.notifyDataChanged();
                }
            }
        };

        dialog.show();
    }

    private void changeTerm() {   //TODO: change name to terms
        models.clear();

        List<String> passBookTypes = new ArrayList<>();
        passBookTypes.add(PassBookType.THREE_MONTH.getText());
        passBookTypes.add(PassBookType.SIX_MONTH.getText());
        //passBookTypes.add(PassBookType.INFINITE.getText());

        models.add(new SpinnerModel(getResources().getString(R.string.passbook_type), null, "", passBookTypes));
        models.add(new TextFieldModel(getString(R.string.min_term), "", "", InputType.TYPE_CLASS_NUMBER));

        dialog.onDialogButtonClick = new OnDialogButtonClick() {
            @Override
            public void onNegativeClick() {

            }

            @Override
            public void onPositiveClick() {
                Integer minTime = Integer.valueOf((String) models.get(1).value);

                if(minTime >= BankRegulation.MIN_DEPOSIT_TIME) {
                    PassBookType passBookType = PassBookType.fromString((String) models.get(0).value);

                    updateMinTime(passBookType, minTime);

                    Toast.makeText(getApplicationContext(), getString(R.string.success), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                } else {
                    models.get(1).isError = true;
                    models.get(1).errorSTr = getString(R.string.value_must_greater_the_zero);
                    dialog.notifyDataChanged();
                }
            }
        };

        dialog.show();
    }

    private void changeMinDepositAmount() {
        models.clear();
        models.add(new TextFieldModel(getString(R.string.min_deposit), "", "", InputType.TYPE_CLASS_NUMBER));

        dialog.onDialogButtonClick = new OnDialogButtonClick() {
            @Override
            public void onNegativeClick() {

            }

            @Override
            public void onPositiveClick() {
                Integer minDeposit = Integer.valueOf((String) models.get(0).value);

                if(minDeposit >= BankRegulation.MIN_DEPOSIT_AMOUNT) {
                    updateMinDeposit(Integer.valueOf((String) models.get(0).value));

                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), getString(R.string.success), Toast.LENGTH_LONG).show();
                } else {
                    models.get(0).isError = true;
                    models.get(0).errorSTr = getString(R.string.value_must_greater_the_zero);
                    dialog.notifyDataChanged();
                }
            }
        };

        dialog.show();
    }

    private void changeNumOfPassbookTypes() {
        CustomDialog customDialog = new CustomDialog(
                this,
                R.layout.body_switch_passbook_types,
                getString(R.string.enable_passbook_types));

        SwitchMaterial swThreeMonthsType;
        SwitchMaterial swSixMonthsType;
        SwitchMaterial swInfiniteType;

        swThreeMonthsType = customDialog.getBody().findViewById(R.id.sw1);
        swSixMonthsType = customDialog.getBody().findViewById(R.id.sw2);
        swInfiniteType = customDialog.getBody().findViewById(R.id.sw3);

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
                Toast.makeText(getApplicationContext(), getString(R.string.success), Toast.LENGTH_LONG).show();
                customDialog.dismiss();
            }
        };

        customDialog.show();
    }

    private void updateInterestRate(PassBookType passBookType, Float value) {
        PassBookRegulation passBookRegulation = getPassBookRegulation(passBookType);
        passBookRegulation.interestRate = value;

        appDatabase.passBookRegulationDAO().insertItem(passBookRegulation);
    }

    private void updateMinTime(PassBookType passBookType, Integer value) {
        PassBookRegulation passBookRegulation = getPassBookRegulation(passBookType);
        passBookRegulation.term = value;

        appDatabase.passBookRegulationDAO().insertItem(passBookRegulation);
    }

    private void updateMinDeposit(Integer value) {
        BankRegulation bankRegulation = getBankRegulation();

        bankRegulation.minDepositAmount = value;

        appDatabase.bankRegulationDAO().updateOrInsertItem(bankRegulation);
    }

    private PassBookRegulation getPassBookRegulation(PassBookType passBookType) {
        PassBookRegulation passBookRegulation = appDatabase.passBookRegulationDAO().getLastPassBookByType(passBookType);

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
        BankRegulation bankRegulation = appDatabase.bankRegulationDAO().getItem(1);

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
