package com.example.passbook.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Window;

import com.example.passbook.R;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.adapters.SpacesItemDecoration;
import com.example.passbook.adapters.MainFuncAdapter;
import com.example.passbook.models.BaseFormModel;
import com.example.passbook.models.DateTimeModel;
import com.example.passbook.models.MainFuncModel;
import com.example.passbook.models.SpinnerModel;
import com.example.passbook.models.TextFieldModel;
import com.example.passbook.utils.ApplicationFunction;
import com.example.passbook.utils.Constant;

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

        List<BaseFormModel> items = new ArrayList<BaseFormModel>();

        List<String> spinnerItems = new ArrayList<String>();

        spinnerItems.add("Android");
        spinnerItems.add("iOS");
        spinnerItems.add("MVVM");
        spinnerItems.add("Flutter");

        items.add(new SpinnerModel("spinner!!", spinnerItems, "hint"));
        items.add(new TextFieldModel("textfield!", "", "hint!", "day la error", InputType.TYPE_CLASS_NUMBER));
        items.add(new DateTimeModel("datetimefield!", "", "hint!"));


        RecyclerView test_layout = findViewById(R.id.test_layout);
        FormAdapter formAdapter = new FormAdapter(this, items);
        test_layout.setAdapter(formAdapter);
        test_layout.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
        switch (item.applicationFunction) {
            case REGISTER_PASSBOOK:
                moveToActivity(RegisterPassBookActivity.class);
                break;
            case GET_DEPOSIT_SLIP:
                break;
            case GET_WITHDRAWAL_SLIP:
                break;
            case SEARCH_PASSBOOKS:
                break;
            case MONTHLY_REPORT:
                break;
            case CHANGE_REGULATIONS:
                break;
            default:
        }
    }

    private void moveToActivity(Class<RegisterPassBookActivity> registerPassBookActivityClass) {
        Intent intent = new Intent(this, registerPassBookActivityClass);
        startActivity(intent);
    }


    private void loadDataToAdapter() {
        items.add(new MainFuncModel(getResources().getString(R.string.fi_register), getResources().getString(R.string.register_passbook), ApplicationFunction.REGISTER_PASSBOOK));
        items.add(new MainFuncModel(getResources().getString(R.string.fi_deposit), getResources().getString(R.string.get_deposit_form), ApplicationFunction.GET_DEPOSIT_SLIP));
        items.add(new MainFuncModel(getResources().getString(R.string.fi_withdraw), getResources().getString(R.string.get_withdraw_form), ApplicationFunction.GET_WITHDRAWAL_SLIP));
        items.add(new MainFuncModel(getResources().getString(R.string.fi_search), getResources().getString(R.string.search_passbooks), ApplicationFunction.SEARCH_PASSBOOKS));
        items.add(new MainFuncModel(getResources().getString(R.string.fi_monthly_report), getResources().getString(R.string.monthly_report), ApplicationFunction.MONTHLY_REPORT));
        items.add(new MainFuncModel(getResources().getString(R.string.fi_change_regulations), getResources().getString(R.string.change_regulations), ApplicationFunction.CHANGE_REGULATIONS));
    }
}