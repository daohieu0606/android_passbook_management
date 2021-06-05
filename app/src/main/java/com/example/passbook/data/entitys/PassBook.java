package com.example.passbook.data.entitys;

import androidx.room.ColumnInfo;

import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.enums.PassbookState;

import java.util.Date;

public abstract class PassBook extends BaseEntity {
    public String customerId;
    public Date creationDate;
    public int amount;
    public PassBookType passBookType;
    public PassbookState passbookState;
}
