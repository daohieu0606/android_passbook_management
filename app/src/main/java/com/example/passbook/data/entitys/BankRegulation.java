package com.example.passbook.data.entitys;

import androidx.room.Entity;

import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.utils.Constant;

@Entity(tableName = Constant.BANK_REGULATION_TABLE)
public class BankRegulation {
    public PassBookType existedPassBookTypes;
    public int minDepositAmount;
}