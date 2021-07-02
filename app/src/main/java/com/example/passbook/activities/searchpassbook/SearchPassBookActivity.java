package com.example.passbook.activities.searchpassbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.passbook.R;
import com.example.passbook.activities.base.BaseActivity;
import com.example.passbook.adapters.SearchPassBookAdapter;
import com.example.passbook.adapters.SpacesItemDecoration;
import com.example.passbook.customviews.IconLabel;
import com.example.passbook.data.models.PassBookItem;
import com.example.passbook.services.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class SearchPassBookActivity extends BaseActivity implements SearchPassbookContract.View {
    private IconLabel btnBack;
    private IconLabel btnMenu;
    private EditText edtSearchKey;
    private TextView txtNothing;
    private RecyclerView rvPassbook;

    private List<PassBookItem> models;
    private SearchPassBookAdapter adapter;
    private boolean isListViewEmpty;

    private SearchPassbookContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pass_book);

        presenter = new SearchPassbookPresenter(this);

        bindingView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        changeVisibleListView(true);
    }

    private void bindingView() {
        btnBack = findViewById(R.id.btnBack);
        btnMenu = findViewById(R.id.txtMenu);
        edtSearchKey = findViewById(R.id.edtSearchKey);
        txtNothing = findViewById(R.id.txtNothing);
        rvPassbook = findViewById(R.id.rvPassbook);

        models = new ArrayList<>();
        adapter = new SearchPassBookAdapter(this, models);

        rvPassbook.setAdapter(adapter);
        rvPassbook.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        rvPassbook.addItemDecoration(new SpacesItemDecoration(20));

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

    private void onSearch() {
        models.clear();
        adapter.notifyDataSetChanged();

        models.addAll(presenter.getPassbook(edtSearchKey.getText().toString()));
        
        if(models.size() == 0) {
            changeVisibleListView(true);
        } else {
            changeVisibleListView(false);
        }

        adapter.notifyDataSetChanged();
    }


    public void changeVisibleListView(boolean isEmpty) {
        if(isListViewEmpty != isEmpty) {
            txtNothing.setVisibility(isEmpty? View.VISIBLE: View.GONE);
            rvPassbook.setVisibility(isEmpty? View.GONE: View.VISIBLE);

            isListViewEmpty = isEmpty;
        }
    }

    @Override
    public void moveToAnotherActivity(Intent intent) {
        if(intent != null) {
            this.startActivity(intent);
        }
    }
}