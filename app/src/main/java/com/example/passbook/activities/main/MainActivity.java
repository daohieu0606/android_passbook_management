package com.example.passbook.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.passbook.R;
import com.example.passbook.activities.base.BaseActivity;
import com.example.passbook.activities.pickupchangeregulation.PickUpChangeRegulationTypeActivity;
import com.example.passbook.activities.pickupreport.PickupReportActivity;
import com.example.passbook.activities.searchpassbook.SearchPassBookActivity;
import com.example.passbook.activities.editdepositslip.EditDepositActivity;
import com.example.passbook.activities.editwithdrawslip.EditWithdrawalActivity;
import com.example.passbook.activities.registerpassbook.RegisterPassBookActivity;
import com.example.passbook.adapters.SpacesItemDecoration;
import com.example.passbook.adapters.MainFuncAdapter;
import com.example.passbook.data.HardCode;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.PassBookRegulation;
import com.example.passbook.data.entitys.TransactionForm;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.models.MainFuncModel;
import com.example.passbook.services.AppDatabase;
import com.example.passbook.utils.ApplicationFunction;
import com.example.passbook.utils.ThemeExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter presenter;
    private RecyclerView rvMainFunction;
    private MainFuncAdapter adapter;
    private List<MainFuncModel> items;

    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeExtension.setTheme(this);

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

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
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