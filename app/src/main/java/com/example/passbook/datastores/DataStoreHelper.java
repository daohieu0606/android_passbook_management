package com.example.passbook.datastores;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataStoreHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PASSBOOK_MANAGER";
    private static final int DATABASE_Version = 1;

    private static final String PASS_BOOK_TABLE_NAME = "PASS_BOOK_TABLE_NAME";
    private static final String CUSTOMER_TABLE_NAME = "PASS_BOOK_TABLE_NAME";
    private static final String CONFIGURATION_TABLE_NAME = "PASS_BOOK_TABLE_NAME";
    private static final String TRANSITION_TABLE_NAME = "PASS_BOOK_TABLE_NAME";

    private static final String ID_KEY = "ID";
    private static final String FullName_KEY = "FullName";
    private static final String Address_KEY = "Address";
    private static final String IdentifyNumber_KEY = "IdentifyNumber";
    private static final String _KEY = "";

    private Context context;
    /* private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRSTNAME + " TEXT );";*/

    private static final String CREATE_CUSTOMER_TABLE_COMMAND = "CREATE TABLE " + CUSTOMER_TABLE_NAME +
            "(" + ID_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FullName_KEY + " TEXT, " +
            Address_KEY + " TEXT, " +
            IdentifyNumber_KEY + " TEXT );";

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
