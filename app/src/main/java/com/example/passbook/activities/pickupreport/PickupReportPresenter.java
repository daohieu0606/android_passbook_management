package com.example.passbook.activities.pickupreport;

import com.example.passbook.activities.base.BaseContract;
import com.example.passbook.activities.base.BasePresenter;

public class PickupReportPresenter extends BasePresenter implements PickupReportContract.Presenter {
    private PickupReportContract.View view;

    public PickupReportPresenter(PickupReportContract.View view) {
        super(view);

        this.view = view;
    }
}
