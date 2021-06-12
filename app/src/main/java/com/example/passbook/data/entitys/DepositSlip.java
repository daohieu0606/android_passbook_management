package com.example.passbook.data.entitys;

import com.example.passbook.data.enums.TransactionFormType;

public class DepositSlip extends TransactionForm {
    public DepositSlip() {
        transactionFormType = TransactionFormType.DEPOSIT;
    }
}
