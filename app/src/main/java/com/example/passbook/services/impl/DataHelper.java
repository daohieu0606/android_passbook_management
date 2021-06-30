package com.example.passbook.services.impl;

import android.content.Context;

import com.example.passbook.daos.BankRegulationDAO;
import com.example.passbook.daos.CustomerDAO;
import com.example.passbook.daos.PassBookDAO;
import com.example.passbook.daos.PassBookRegulationDAO;
import com.example.passbook.daos.TransactionFormDAO;
import com.example.passbook.services.AppDatabase;
import com.example.passbook.services.IDataHelper;

public class DataHelper implements IDataHelper {
    private AppDatabase appDatabase;

    @Override
    public CustomerDAO customerDAO() {
        return null;
    }

    @Override
    public PassBookDAO passBookDAO() {
        return null;
    }

    @Override
    public TransactionFormDAO transactionFormDAO() {
        return null;
    }

    @Override
    public BankRegulationDAO bankRegulationDAO() {
        return null;
    }

    @Override
    public PassBookRegulationDAO passBookRegulationDAO() {
        return null;
    }
}
