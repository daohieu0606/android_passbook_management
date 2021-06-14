package com.example.passbook.activities;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.room.Room;

import com.example.passbook.R;
import com.example.passbook.customviews.DialogHaveListView;
import com.example.passbook.daos.CustomerDAO;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.models.BaseFormModel;
import com.example.passbook.data.models.DateTimeModel;
import com.example.passbook.data.models.SpinnerModel;
import com.example.passbook.data.models.TextFieldModel;
import com.example.passbook.services.AppDatabase;
import com.example.passbook.utils.Constant;
import com.example.passbook.intefaces.OnDialogButtonClick;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDataActivity extends BaseActivity implements View.OnClickListener {
    private Button btnGetAll;
    private Button btnGet;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnInsert;
    private Button btnDeleteAll;

    private CustomerDAO customerDAO;
    List<BaseFormModel> models = new ArrayList<>();

    public static final String TAG = "TestFormTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        containerLayout = R.layout.activity_test_data;
        title = "Register PassBook";
        super.onCreate(savedInstanceState);


        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        customerDAO = db.customerDAO();

        TestFunc();
    }

    private void TestFunc() {
        btnGetAll = findViewById(R.id.btnGetAll);
         btnGet = findViewById(R.id.btnGet);
         btnUpdate = findViewById(R.id.btnUpdate);
         btnDelete = findViewById(R.id.btnDelete);
         btnInsert = findViewById(R.id.btnInsert);
        btnDeleteAll = findViewById(R.id.btnDeleteAll);

         btnGetAll.setOnClickListener(this::onClick);
         btnGet.setOnClickListener(this::onClick);
         btnDelete.setOnClickListener(this::onClick);
         btnInsert.setOnClickListener(this::onClick);
         btnUpdate.setOnClickListener(this::onClick);
        btnDeleteAll.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGetAll:
                handleGetAll();
                break;
            case R.id.btnGet:
                break;
            case R.id.btnUpdate:
                break;
            case R.id.btnDelete:
                break;
            case R.id.btnInsert:
                break;
            case R.id.btnDeleteAll:
                break;
        }
    }

    private void handleGetAll() {
        List<String> passBookTypes = new ArrayList<>();
        passBookTypes.add(PassBookType.THREE_MONTH.getText());
        passBookTypes.add(PassBookType.SIX_MONTH.getText());
        passBookTypes.add(PassBookType.INFINITE.getText());

        models.add(new TextFieldModel(Constant.PASSBOOK_ID, "", "", InputType.TYPE_CLASS_NUMBER));
        models.add(new SpinnerModel(Constant.PASSBOOK_TYPE, null, "", passBookTypes));
        models.add(new DateTimeModel(Constant.REGISTER_DATE, new Date(), ""));

        DialogHaveListView dialogHaveListView = new DialogHaveListView(this, models);
        dialogHaveListView.show();
        dialogHaveListView.onDialogButtonClick = new OnDialogButtonClick() {
            @Override
            public void onNegativeClick() {
                ;
            }

            @Override
            public void onPositiveClick() {
                handlePositiveClick();
            }
        };
    }

    private void handlePositiveClick() {
        Toast.makeText(this.getApplicationContext(), "hello world", Toast.LENGTH_LONG).show();
    }
}