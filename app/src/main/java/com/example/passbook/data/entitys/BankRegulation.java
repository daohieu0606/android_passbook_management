package com.example.passbook.data.entitys;

import androidx.room.Entity;

import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.utils.Constant;

@Entity(tableName = Constant.BANK_REGULATION_TABLE)
public class BankRegulation extends BaseEntity {
    public static final int MIN_DEPOSIT_TIME = 15;
    public static final int MIN_DEPOSIT_AMOUNT = 100000;

    public int existedPassBookTypes;
    public int minDepositAmount;
}
