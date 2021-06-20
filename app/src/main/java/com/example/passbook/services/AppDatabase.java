package com.example.passbook.services;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Transaction;
import androidx.room.TypeConverters;

import com.example.passbook.converters.DateConverter;
import com.example.passbook.daos.BankRegulationDAO;
import com.example.passbook.daos.CustomerDAO;
import com.example.passbook.daos.PassBookDAO;
import com.example.passbook.daos.PassBookRegulationDAO;
import com.example.passbook.daos.TransactionFormDAO;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.PassBookRegulation;
import com.example.passbook.data.entitys.TransactionForm;

@Database(entities = {Customer.class,
        PassBook.class,
        TransactionForm.class,
        BankRegulation.class,
        PassBookRegulation.class},
        version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CustomerDAO customerDAO();
    public abstract PassBookDAO passBookDAO();
    public abstract TransactionFormDAO transactionFormDAO();
    public abstract BankRegulationDAO bankRegulationDAO();
    public abstract PassBookRegulationDAO passBookRegulationDAO();
}
