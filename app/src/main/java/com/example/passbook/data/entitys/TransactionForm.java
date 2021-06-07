package com.example.passbook.data.entitys;

import androidx.room.Entity;

import com.example.passbook.data.enums.TransactionFormType;
import com.example.passbook.utils.Constant;

import java.util.Date;

@Entity(tableName = Constant.TRANSACTION_FORM_TABLE)
public class TransactionForm extends BaseEntity {
    public String passBookId;
    public String customerId;
    public Date transactionDateTime;
    public float amount;
    public TransactionFormType transactionFormType;
}
