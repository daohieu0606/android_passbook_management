package com.example.passbook.datastores;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataStoreHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PASSBOOK_MANAGER";
    private static final String PASS_BOOK_TABLE_NAME = "PASS_BOOK_TABLE_NAME";
    private static final String CUSTOMER_TABLE_NAME = "PASS_BOOK_TABLE_NAME";
    private static final String CONFIGURATION_TABLE_NAME = "PASS_BOOK_TABLE_NAME";
    private static final String TRANSITION_TABLE_NAME = "PASS_BOOK_TABLE_NAME";
    private static final int DATABASE_Version = 1;

    private Context context;

    public DataStoreHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
