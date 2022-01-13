package com.example.passbook.activities.main;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passbook.activities.base.BasePresenter;
import com.example.passbook.activities.editdepositslip.EditDepositActivity;
import com.example.passbook.activities.editwithdrawslip.EditWithdrawalActivity;
import com.example.passbook.activities.changeregulation.ChangeRegulationTypeActivity;
import com.example.passbook.activities.pickupreport.PickupReportActivity;
import com.example.passbook.activities.registerpassbook.RegisterPassBookActivity;
import com.example.passbook.activities.searchpassbook.SearchPassBookActivity;
import com.example.passbook.data.models.MainFuncModel;

public class MainPresenter extends BasePresenter implements MainContract.Presenter {
    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        super(view);
        this.view = view;
    }

    @Override
    public void handleItemClick(MainFuncModel item) {
        Intent intent = null;

        switch (item.applicationFunction) {
            case REGISTER_PASSBOOK:
                intent = new Intent((AppCompatActivity)view, RegisterPassBookActivity.class);
                break;

            case GET_DEPOSIT_SLIP:
                intent = new Intent((AppCompatActivity)view, EditDepositActivity.class);
                break;

            case GET_WITHDRAWAL_SLIP:
                intent = new Intent((AppCompatActivity)view, EditWithdrawalActivity.class);
                break;

            case SEARCH_PASSBOOKS:
                intent = new Intent((AppCompatActivity)view, SearchPassBookActivity.class);
                break;

            case REPORT:
                intent = new Intent((AppCompatActivity)view, PickupReportActivity.class);
                break;

            case CHANGE_REGULATIONS:
                intent = new Intent((AppCompatActivity)view, ChangeRegulationTypeActivity.class);
                break;

            default:

        }

        view.moveToAnotherActivity(intent);
    }
}
