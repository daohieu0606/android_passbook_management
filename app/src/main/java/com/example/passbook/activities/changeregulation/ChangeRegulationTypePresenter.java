package com.example.passbook.activities.changeregulation;

import com.example.passbook.activities.base.BasePresenter;

public class ChangeRegulationTypePresenter extends BasePresenter
        implements ChangeRegulationTypeContract.Presenter {
    private ChangeRegulationTypeContract.View view;

    public ChangeRegulationTypePresenter(ChangeRegulationTypeContract.View view) {
        super(view);

        this.view = view;
    }
}
