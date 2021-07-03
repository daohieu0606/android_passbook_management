package com.example.passbook.activities.main;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.passbook.R;
import com.example.passbook.activities.base.BaseActivity;
import com.example.passbook.adapters.SpacesItemDecoration;
import com.example.passbook.adapters.MainFuncAdapter;
import com.example.passbook.data.models.MainFuncModel;
import com.example.passbook.utils.ApplicationFunction;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainContract.View {
    private MainContract.Presenter presenter;
    private RecyclerView rvMainFunction;
    private MainFuncAdapter adapter;
    private List<MainFuncModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);

        init();
        presenter.intData();
    }

    private void init() {
        items = new ArrayList<>();
        rvMainFunction = findViewById(R.id.rvMainFunction);
        adapter = new MainFuncAdapter(this, items);

        loadDataToAdapter();

        adapter.setOnItemClick(new MainFuncAdapter.OnItemAdapterClickListener() {
            @Override
            public void OnItemClicked(MainFuncModel item) {
                presenter.handleItemClick(item);
            }
        });

        rvMainFunction.setAdapter(adapter);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        rvMainFunction.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        rvMainFunction.setLayoutManager(new GridLayoutManager(this, 3));
    }

    private void loadDataToAdapter() {
        items.add(new MainFuncModel(getResources().getString(R.string.fi_register), getResources().getString(R.string.register_passbook), ApplicationFunction.REGISTER_PASSBOOK));
        items.add(new MainFuncModel(getResources().getString(R.string.fi_deposit), getResources().getString(R.string.get_deposit_form), ApplicationFunction.GET_DEPOSIT_SLIP));
        items.add(new MainFuncModel(getResources().getString(R.string.fi_withdraw), getResources().getString(R.string.get_withdraw_form), ApplicationFunction.GET_WITHDRAWAL_SLIP));
        items.add(new MainFuncModel(getResources().getString(R.string.fi_search), getResources().getString(R.string.search_passbooks), ApplicationFunction.SEARCH_PASSBOOKS));
        items.add(new MainFuncModel(getResources().getString(R.string.fi_monthly_report), getResources().getString(R.string.report), ApplicationFunction.REPORT));
        items.add(new MainFuncModel(getResources().getString(R.string.fi_change_regulations), getResources().getString(R.string.change_regulations), ApplicationFunction.CHANGE_REGULATIONS));
    }

    @Override
    public void moveToAnotherActivity(Intent intent) {
        if(intent != null) {
            this.startActivity(intent);
        }
    }
}