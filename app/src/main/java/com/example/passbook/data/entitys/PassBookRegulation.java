package com.example.passbook.data.entitys;

import com.example.passbook.data.enums.PassBookType;

import java.util.Date;

public class PassBookRegulation extends BaseEntity {
    public PassBookType passBookType;
    public Date creationDateTime;
    public int term;
    public float interestRate;
}
