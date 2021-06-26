package com.example.passbook.activities.pickupchangeregulation;

import com.example.passbook.activities.base.BaseContract;
import com.example.passbook.activities.base.BasePresenter;

public class PickUpChangeRegulationTypePresenter extends BasePresenter
        implements PickUpChangeRegulationTypeContract.Presenter {
    private PickUpChangeRegulationTypeContract.View view;

    public PickUpChangeRegulationTypePresenter(PickUpChangeRegulationTypeContract.View view) {
        super(view);

        this.view = view;
    }
}
