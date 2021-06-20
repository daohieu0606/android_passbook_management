package com.example.passbook.data.entitys;

import androidx.room.Transaction;

import com.example.passbook.data.enums.TransactionFormType;

public class WithdrawalSlip extends TransactionForm {
    public WithdrawalSlip() {
        transactionFormType = TransactionFormType.WITHDRAWAL;
    }
}
