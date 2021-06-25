package com.example.passbook.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.passbook.R;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMainFunction;
    private MainFuncAdapter adapter;
    private List<MainFuncModel> items;

    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        init();
        initBankRegulation();
        intPassbookRegulation();
        hardcode(); //TODO: remove hard code
    }

    private void intPassbookRegulation() {
        if (appDatabase.passBookRegulationDAO().getItems().size() < 3) {
            PassBookRegulation threeMonthsRegulation = new PassBookRegulation();
            threeMonthsRegulation.passBookType = PassBookType.THREE_MONTH;
            threeMonthsRegulation.creationDateTime = new Date();
            threeMonthsRegulation.term = 3 * 30;
            threeMonthsRegulation.Id = 1;
            threeMonthsRegulation.interestRate = 0.005f;

            PassBookRegulation sixMonthsRegulation = new PassBookRegulation();
            sixMonthsRegulation.passBookType = PassBookType.SIX_MONTH;
            sixMonthsRegulation.creationDateTime = new Date();
            sixMonthsRegulation.term = 6 * 30;
            sixMonthsRegulation.Id = 2;
            sixMonthsRegulation.interestRate = 0.0055f;

            PassBookRegulation infiniteRegulation = new PassBookRegulation();
            infiniteRegulation.passBookType = PassBookType.INFINITE;
            infiniteRegulation.creationDateTime = new Date();
            infiniteRegulation.term = 0;
            infiniteRegulation.Id = 3;
            infiniteRegulation.interestRate = 0.0015f;

            appDatabase.passBookRegulationDAO().insertItem(threeMonthsRegulation);
            appDatabase.passBookRegulationDAO().insertItem(sixMonthsRegulation);
            appDatabase.passBookRegulationDAO().insertItem(infiniteRegulation);
        }
    }

    private void hardcode() {
        for (Customer customer:
                HardCode.HardCodeCustomer.getCustomers()) {
            appDatabase.customerDAO().insertItem(customer);
        }

        for (PassBook passBook :
                HardCode.HardCodePassbook.getPassBooks()) {
            appDatabase.passBookDAO().insertItem(passBook);
        }

        for (TransactionForm transactionForm :
                HardCode.HardCodeTransactionForm.getTransactionForms()) {
            appDatabase.transactionFormDAO().insertItem(transactionForm);
        }
    }

    private void initBankRegulation() {
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

    private void init() {
        items = new ArrayList<>();
        rvMainFunction = findViewById(R.id.rvMainFunction);
        adapter = new MainFuncAdapter(this, items);

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

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
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
                intent = new Intent(this, SearchPassBookActivity.class);
                break;

            case REPORT:
                intent = new Intent(this, PickupReportActivity.class);
                break;

            case CHANGE_REGULATIONS:
                intent = new Intent(this, PickUpChangeRegulationTypeActivity.class);
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