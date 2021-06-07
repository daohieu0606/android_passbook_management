package com.example.passbook.datastores;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.passbook.daos.CustomerDAO;
import com.example.passbook.data.entitys.Customer;

@Database(entities = {Customer.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CustomerDAO customerDAO();
}
