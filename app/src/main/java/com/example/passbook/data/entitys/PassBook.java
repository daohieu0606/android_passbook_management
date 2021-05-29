package com.example.passbook.data.entitys;

import com.example.passbook.data.enums.PassBookType;

import java.util.Date;

public abstract class PassBook extends BaseEntity {
    public String customerId;
    public Date creationDate;
    public int deposit;
    public PassBookType passBookType;
}
