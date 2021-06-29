package com.example.passbook.activities.searchpassbook;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passbook.activities.base.BaseContract;
import com.example.passbook.activities.base.BasePresenter;
import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.models.PassBookItem;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchPassbookPresenter extends BasePresenter implements SearchPassbookContract.Presenter {
    private SearchPassbookContract.View view;

    public SearchPassbookPresenter(SearchPassbookContract.View view) {
        super(view);

        this.view = view;
    }

    @Override
    public List<PassBookItem> getPassbook(String key) {
        List<PassBookItem> models = new ArrayList<>();

        if(!StringUtils.isEmpty(key)) {
            String queryStr = "%" + key + "%";

            insertToModel(models, fetchByPassbookId(queryStr));
            insertToModel(models, fetchByCustomName(queryStr));

        }
        return models;
    }

    private List<PassBook> fetchByCustomName(String queryStr) {
        List<Customer> customers = appDatabase.customerDAO().getItemsByName(queryStr);
        List<PassBook> passBooks = new ArrayList<>();

        for (Customer customer :
                customers) {
            PassBook passBook = appDatabase.passBookDAO().getItemByCustomerId(customer.Id);

            if(passBook != null) {
                passBooks.add(passBook);
            }
        }

        return passBooks;
    }

    private List<PassBook> fetchByPassbookId(String queryStr) {
         return appDatabase.passBookDAO().getItemsById(queryStr);
    }

    private void insertToModel(List<PassBookItem> models, List<PassBook> passBooks) {
        for (PassBook passBook :
                passBooks) {
            PassBookItem passBookItem = PassBookItem.passBookEntityToModel((AppCompatActivity)view, passBook);

            models.add(passBookItem);
        }
    }
}
