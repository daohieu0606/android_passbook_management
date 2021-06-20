package com.example.passbook.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.passbook.R;
import com.example.passbook.adapters.SearchPassBookAdapter;
import com.example.passbook.adapters.SpacesItemDecoration;
import com.example.passbook.customviews.IconLabel;
import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.models.PassBookItem;
import com.example.passbook.services.AppDatabase;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchPassBookActivity extends AppCompatActivity {
    private IconLabel btnBack;
    private IconLabel btnMenu;
    private EditText edtSearchKey;
    private TextView txtNothing;
    private RecyclerView rvPassbook;

    private List<PassBookItem> models;
    private SearchPassBookAdapter adapter;

    private boolean isListViewEmpty;
    protected AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pass_book);

        bindingView();

        initData();

        setEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        changeVisibleListView(true);
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    private void setEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edtSearchKey.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    onSearch();
                    return true;
                }
                return false;
            }
        });
    }

    private void bindingView() {
        btnBack = findViewById(R.id.btnBack);
        btnMenu = findViewById(R.id.txtMenu);
        edtSearchKey = findViewById(R.id.edtSearchKey);
        txtNothing = findViewById(R.id.txtNothing);
        rvPassbook = findViewById(R.id.rvPassbook);
    }

    private void initData() {
        models = new ArrayList<>();
        adapter = new SearchPassBookAdapter(this, models);

        rvPassbook.setAdapter(adapter);
        rvPassbook.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        rvPassbook.addItemDecoration(new SpacesItemDecoration(20));
    }

    private void onSearch() {
        models.clear();
        adapter.notifyDataSetChanged();

        if(StringUtils.isEmpty(edtSearchKey.getText().toString()) || edtSearchKey.getText().toString() == null) {
            changeVisibleListView(true);
        } else {
            fetchData();
            changeVisibleListView(false);
        }

        adapter.notifyDataSetChanged();
    }

    private void fetchData() {
        String queryStr = "%" + edtSearchKey.getText().toString() + "%";

        fetchByPassbookId(queryStr);
        fetchByCustomName(queryStr);
    }

    private void fetchByCustomName(String queryStr) {
        List<Customer> customers = appDatabase.customerDAO().getItemsByName(queryStr);
        List<PassBook> passBooks = new ArrayList<>();

        for (Customer customer :
                customers) {
            PassBook passBook = appDatabase.passBookDAO().getItemByCustomerId(customer.Id);

            if(passBook != null) {
                passBooks.add(passBook);
            }
        }

        insertToModel(passBooks);
    }

    private void fetchByPassbookId(String queryStr) {
        List<PassBook> passBooks = appDatabase.passBookDAO().getItemsById(queryStr);

        insertToModel(passBooks);
    }

    private void insertToModel(List<PassBook> passBooks) {
        for (PassBook passBook :
                passBooks) {
            PassBookItem passBookItem = PassBookItem.passBookEntityToModel(getApplicationContext(), passBook);

            models.add(passBookItem);
        }
    }

    private void changeVisibleListView(boolean isEmpty) {
        if(isListViewEmpty != isEmpty) {
            txtNothing.setVisibility(isEmpty? View.VISIBLE: View.GONE);
            rvPassbook.setVisibility(isEmpty? View.GONE: View.VISIBLE);

            isListViewEmpty = isEmpty;
        }
    }
}