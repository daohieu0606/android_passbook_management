package com.example.passbook.services;

import com.example.passbook.daos.BankRegulationDAO;
import com.example.passbook.daos.CustomerDAO;
import com.example.passbook.daos.PassBookDAO;
import com.example.passbook.daos.PassBookRegulationDAO;
import com.example.passbook.daos.TransactionFormDAO;

public interface IDataHelper {
    public abstract CustomerDAO customerDAO();
    public abstract PassBookDAO passBookDAO();
    public abstract TransactionFormDAO transactionFormDAO();
    public abstract BankRegulationDAO bankRegulationDAO();
    public abstract PassBookRegulationDAO passBookRegulationDAO();
}
