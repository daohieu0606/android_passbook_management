package com.example.passbook.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.room.Room;

import com.example.passbook.R;
import com.example.passbook.daos.CustomerDAO;
import com.example.passbook.data.entitys.Customer;
import com.example.passbook.services.AppDatabase;

import java.util.List;

public class TestDataActivity extends BaseActivity implements View.OnClickListener {
    private Button btnGetAll;
    private Button btnGet;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnInsert;
    private Button btnDeleteAll;

    private CustomerDAO customerDAO;

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
                handleGet();
                break;
            case R.id.btnUpdate:
                handleUpdate();
                break;
            case R.id.btnDelete:
                handleDelete();
                break;
            case R.id.btnInsert:
                handleInsert();
                break;
            case R.id.btnDeleteAll:
                handleDeleteAll();
                break;
        }
    }

    private void handleDelete() {
        List<Customer> customerList = customerDAO.getItems();

        if(customerList.size() > 0) {
            customerDAO.deleteItem(customerList.get(0));
            Log.i(TAG, "delete item success");
        }
    }

    private void handleDeleteAll() {
        customerDAO.deleteAll();
        Log.i(TAG, "delete all success");
    }

    private void handleInsert() {
       Customer customer = new Customer();
       customer.fullName = "nguyen van a";
       customer.address = "duong so 1";
       customer.identifyNumber = java.util.UUID.randomUUID().toString();

       customerDAO.insertItem(customer);
       Log.i(TAG, "insert success: " +customer.identifyNumber);
    }

    private void handleUpdate() {
        Customer customer = customerDAO.getItem(11);

        if(customer != null) {
            customer.identifyNumber = "abcfsadklfjklds";
            customerDAO.updateOrInsertItem(customer);
            Log.i(TAG, "update successfully");
        }else {
            Log.i(TAG, "Update failed");
        }
    }

    private void handleGet() {
        Customer customer = customerDAO.getItem(11);

        if(customer != null) {
            Log.i(TAG, "get success: " + customer.identifyNumber);
        } else {
            Log.i(TAG, "get fail");
        }
    }

    private void handleGetAll() {
        List<Customer> customerList = customerDAO.getItems();
        Log.i(TAG, "count: " + String.valueOf(customerList.size()));

        for (Customer customer :
                customerList) {
            Log.i(TAG, String.valueOf(customer.Id));
            Log.i(TAG, String.valueOf(customer.identifyNumber));
        }
    }
}