package com.example.passbook.activities.searchpassbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passbook.R;
import com.example.passbook.activities.base.BaseActivity;
import com.example.passbook.activities.registerpassbook.RegisterPassBookActivity;
import com.example.passbook.adapters.SearchPassBookAdapter;
import com.example.passbook.adapters.SpacesItemDecoration;
import com.example.passbook.customviews.IconLabel;
import com.example.passbook.daos.PassBookDAO;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.models.PassBookItem;
import com.example.passbook.services.AppDatabase;
import com.example.passbook.services.IMenuFunctionService;
import com.example.passbook.services.ServiceLocator;
import com.example.passbook.services.impl.MenuFunctionService;
import com.example.passbook.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class SearchPassBookActivity extends BaseActivity
        implements SearchPassbookContract.View, PopupMenu.OnMenuItemClickListener {
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

    @SuppressLint("ClickableViewAccessibility")
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

        edtSearchKey.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if(event.getAction() == MotionEvent.ACTION_UP) {
                if(event.getRawX() >= (edtSearchKey.getRight() - edtSearchKey.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    onSearch();

                    return true;
                }
            }
            return false;
        });

        btnMenu.setOnClickListener(v -> {
            showPopup(v);
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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Log.i(MyTag, "on item menu clicked");
        IMenuFunctionService menuFunctionService = ServiceLocator.getInstance().getService(IMenuFunctionService.class);

        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.deleteItem:
                performDeletePassbook();
                return true;

            case R.id.editItem:
                performEditPassbook();
                return true;

            case R.id.btnLanguage:
                menuFunctionService.showChangeLanguageDialog(this);
                return true;

            case R.id.btnTheme:
                menuFunctionService.showChangeThemeDialog(this);
                return true;

            case R.id.btnAbout:
                menuFunctionService.goToAboutActivity(this);
                return true;

            default:
                return false;
        }
    }

    private void performEditPassbook() {
        Intent intent = new Intent(this, RegisterPassBookActivity.class);
        intent.putExtra(Constant.PASSBOOK_ID, adapter.getSelectedItem().passBookId);
        this.startActivity(intent);
    }

    private void performDeletePassbook() {
        PassBookItem passBookModel = adapter.getSelectedItem();

        if(passBookModel != null) {
            PassBookDAO passBookDAO = appDatabase.passBookDAO();
            PassBook passBook = passBookDAO.getItem(Integer.valueOf(passBookModel.passBookId));

            Log.i(MyTag, "list pasbook count: " + passBookDAO.getItems().size());

            if(passBook != null) {
                passBookDAO.deleteItem(passBook);

                int itemPosition = models.indexOf(passBookModel);
                models.remove(passBookModel);
                adapter.notifyItemRemoved(itemPosition);
            }

            Log.i(MyTag, "list pasbook count: " + passBookDAO.getItems().size());
        }
    }

    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.setOnMenuItemClickListener(this);
        popup.show();

    }
}