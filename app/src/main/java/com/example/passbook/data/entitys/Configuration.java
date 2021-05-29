package com.example.passbook.data.entitys;

import com.example.passbook.data.enums.PassBookType;

public class Configuration extends BaseEntity {
    public PassBookType passBookType;
    public float minDepositAmount;
    public int minDepositDate;
    public float interestRate;
}
