package com.example.passbook.data.entitys;

import com.example.passbook.data.enums.TransactionFormType;

import java.util.Date;

public class TransactionForm extends BaseEntity {
    public String passBookId;
    public String customerId;
    public Date transactionDateTime;
    public float amount;
    public TransactionFormType transactionFormType;
}
