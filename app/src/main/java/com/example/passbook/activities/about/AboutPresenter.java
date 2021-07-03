package com.example.passbook.activities.about;

import com.example.passbook.activities.base.BaseContract;
import com.example.passbook.activities.base.BasePresenter;
import com.example.passbook.data.enums.ThemeType;

public class AboutPresenter extends BasePresenter implements AboutContract.Presenter {
    private AboutContract.View view;

    public AboutPresenter(AboutContract.View view) {
        super(view);

        this.view = view;
    }
}
