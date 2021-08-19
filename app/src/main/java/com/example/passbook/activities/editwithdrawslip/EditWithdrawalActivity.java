package com.example.passbook.activities.editwithdrawslip;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;

import com.example.passbook.R;
import com.example.passbook.activities.form.FormHaveSubmitButtonActivity;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.customviews.NotificationPopup;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.WithdrawalSlip;
import com.example.passbook.data.enums.DatePickerType;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.models.DateTimeModel;
import com.example.passbook.data.models.TextFieldModel;
import com.example.passbook.intefaces.OnDismissListener;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditWithdrawalActivity extends FormHaveSubmitButtonActivity implements EditWithdrawSlipContract.View {
    private EditWithdrawSlipContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getString(R.string.withdrawal_slip);

        presenter = new EditWithdrawSlipPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter.setItemChangeListener(position -> {
            Log.i("mytag", String.valueOf(position));

            if(position == 0){
                String passBookIdStr = (String) models.get(0).value;
                models.get(1).value = "";
                models.get(1).isEnable = true;
                models.get(3).value = "";
                models.get(3).isEnable = true;

                if(!StringUtils.isEmpty(passBookIdStr)) {
                    int passBookId = Integer.valueOf(passBookIdStr);
                    PassBook passBook = appDatabase.passBookDAO().getItem(passBookId);

                    if(passBook != null) {
                        models.get(1).value = String.valueOf(passBook.customerId);
                        models.get(1).isEnable = false;

                        if(passBook.passBookType != PassBookType.INFINITE) {
                            models.get(3).value = String.valueOf(passBook.amount);
                            models.get(3).isEnable = false;
                        }
                    }
                }

                lst_input.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyItemChanged(1);
                        adapter.notifyItemChanged(3);
                    }
                });
            }
        });
    }

    @Override
    protected void initModelAndAdapter() {
        models = new ArrayList<>();

        models.add(new TextFieldModel(getString(R.string.passbook_id),
                null,
                "",
                InputType.TYPE_CLASS_NUMBER));
        models.add(new TextFieldModel(getString(R.string.customer_id),
                null,
                "",
                InputType.TYPE_CLASS_NUMBER));
        models.add(new DateTimeModel(getString(R.string.withdraw_date),
                null,
                "",
                DatePickerType.NORMAL));
        models.add(new TextFieldModel(getString(R.string.amount),
                null,
                "",
                InputType.TYPE_CLASS_NUMBER));

        adapter = new FormAdapter(this, models, lst_input);
    }

    @Override
    protected void getDataFromViewAndCallPresenterHandle() {
        WithdrawalSlip withdrawalSlip = getWithdrawalSlip();
        presenter.handleSubmit(withdrawalSlip);
    }

    private WithdrawalSlip getWithdrawalSlip() {
        WithdrawalSlip withdrawalSlip = new WithdrawalSlip();

        withdrawalSlip.passBookId = Integer.valueOf((String) models.get(0).value);
        withdrawalSlip.customerId = Integer.valueOf((String) models.get(1).value);
        withdrawalSlip.transactionDateTime = (Date) models.get(2).value;
        withdrawalSlip.amount = Integer.valueOf((String) models.get(3).value);

        return withdrawalSlip;
    }

    @Override
    public void setPassbookIsNotExistError() {
        models.get(0).isError = true;
        models.get(0).errorSTr = getString(R.string.passbook_id_is_not_existed);
    }

    @Override
    public void setPassbookIsClosedError() {
        models.get(0).isError = true;
        models.get(0).errorSTr = getString(R.string.passbook_has_closed);
    }

    @Override
    public void setCustomerIdWrongError() {
        models.get(1).isError = true;
        models.get(1).errorSTr = getString(R.string.customer_id_is_wrong);
    }

    @Override
    public void setMinPeriodError(int term) {
        models.get(2).isError = true;
        models.get(2).errorSTr = String.format(getString(R.string.you_must_deposit_at_least_dates), term);
    }

    @Override
    public void setOverDepositError(int currentDeposit) {
        models.get(3).isError = true;
        models.get(3).errorSTr = getString(R.string.amount_must_be_smaller_than_current_deposit) + String.valueOf(currentDeposit);
    }

    @Override
    public void notifyInterest(float interest) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        String interestStr = formatter.format(interest);

        NotificationPopup notificationPopup = new NotificationPopup(
                this,
                getString(R.string.interest),
                getString(R.string.your_interest) + interestStr,
                () -> {
                    finish();
                });

        notificationPopup.show();
    }
}
