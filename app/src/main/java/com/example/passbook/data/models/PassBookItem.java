package com.example.passbook.data.models;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.services.AppDatabase;

public class PassBookItem {
    public String customerName;
    public String passBookId;
    public String amount;
    public String passBookType;

    public static PassBookItem passBookEntityToModel(Context context, PassBook passBook) {
        PassBookItem result = new PassBookItem();

        result.passBookType = passBook.passBookType.getText((AppCompatActivity) context);
        result.amount = String.valueOf(passBook.amount);
        result.passBookId = String.valueOf(passBook.Id);

        AppDatabase appDatabase = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        Customer customer = appDatabase.customerDAO().getItem(passBook.customerId);

        if(customer != null) {
            result.customerName = customer.fullName;
        }

        return result;
    }
}
