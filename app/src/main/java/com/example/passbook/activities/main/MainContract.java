package com.example.passbook.activities.main;

import com.example.passbook.activities.base.BaseContract;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.models.MainFuncModel;

import java.util.Date;
import java.util.List;

public interface MainContract {
    interface View extends BaseContract.View {
    }

    interface Presenter extends BaseContract.Presenter {
        void handleItemClick(MainFuncModel item);
    }
}
