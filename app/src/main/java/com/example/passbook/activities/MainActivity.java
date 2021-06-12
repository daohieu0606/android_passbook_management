package com.example.passbook.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.passbook.R;
import com.example.passbook.adapters.SpacesItemDecoration;
import com.example.passbook.adapters.MainFuncAdapter;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.models.MainFuncModel;
import com.example.passbook.services.AppDatabase;
import com.example.passbook.utils.ApplicationFunction;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMainFunction;
    private MainFuncAdapter adapter;
    private List<MainFuncModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Init();
        InitBankRegulation();
    }

    private void InitBankRegulation() {
        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        if(appDatabase.bankRegulationDAO().getItems().size() == 0) {
            BankRegulation bankRegulation = new BankRegulation();
            bankRegulation.existedPassBookTypes = PassBookType.THREE_MONTH.getValue()
                                                | PassBookType.SIX_MONTH.getValue()
                                                | PassBookType.INFINITE.getValue();
            bankRegulation.minDepositAmount = 100000;
            bankRegulation.Id = 1;

            appDatabase.bankRegulationDAO().insertItem(bankRegulation);
        }
    }

    private void Init() {
        items = new ArrayList<>();
        rvMainFunction = findViewById(R.id.rvMainFunction);
        adapter = new MainFuncAdapter(getApplicationContext(), items);

        loadDataToAdapter();

        adapter.setOnItemClick(new MainFuncAdapter.OnItemAdapterClickListener() {
            @Override
            public void OnItemClicked(MainFuncModel item) {
                handleItemClick(item);
            }
        });

        rvMainFunction.setAdapter(adapter);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        rvMainFunction.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        rvMainFunction.setLayoutManager(new GridLayoutManager(this, 3));
    }

    private void handleItemClick(MainFuncModel item) {

        Intent intent = null;

        switch (item.applicationFunction) {
            case REGISTER_PASSBOOK:
                intent = new Intent(this, RegisterPassBookActivity.class);
                break;

            case GET_DEPOSIT_SLIP:
                intent = new Intent(this, EditDepositActivity.class);
                break;

            case GET_WITHDRAWAL_SLIP:
                intent = new Intent(this, EditWithdrawalActivity.class);
                break;

            case SEARCH_PASSBOOKS:
                break;

            case REPORT:
                intent = new Intent(this, PickupReportActivity.class);
                break;

            case CHANGE_REGULATIONS:
                break;

            default:

        }

        if(intent != null) {
            startActivity(intent);
        }
    }

    private void loadDataToAdapter() {
        items.add(new MainFuncModel(getResources().getString(R.string.fi_register), getResources().getString(R.string.register_passbook), ApplicationFunction.REGISTER_PASSBOOK));
        items.add(new MainFuncModel(getResources().getString(R.string.fi_deposit), getResources().getString(R.string.get_deposit_form), ApplicationFunction.GET_DEPOSIT_SLIP));
        items.add(new MainFuncModel(getResources().getString(R.string.fi_withdraw), getResources().getString(R.string.get_withdraw_form), ApplicationFunction.GET_WITHDRAWAL_SLIP));
        items.add(new MainFuncModel(getResources().getString(R.string.fi_search), getResources().getString(R.string.search_passbooks), ApplicationFunction.SEARCH_PASSBOOKS));
        items.add(new MainFuncModel(getResources().getString(R.string.fi_monthly_report), getResources().getString(R.string.report), ApplicationFunction.REPORT));
        items.add(new MainFuncModel(getResources().getString(R.string.fi_change_regulations), getResources().getString(R.string.change_regulations), ApplicationFunction.CHANGE_REGULATIONS));
    }
}