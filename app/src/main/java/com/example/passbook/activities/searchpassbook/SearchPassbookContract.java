package com.example.passbook.activities.searchpassbook;

import com.example.passbook.activities.base.BaseContract;
import com.example.passbook.data.models.PassBookItem;

import java.util.List;

public interface SearchPassbookContract {
    interface View extends BaseContract.View {
    }

    interface Presenter extends BaseContract.Presenter {

        List<PassBookItem> getPassbook(String key);
    }
}
