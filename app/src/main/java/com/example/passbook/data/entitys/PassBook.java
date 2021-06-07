package com.example.passbook.data.entitys;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.enums.PassbookState;
import com.example.passbook.utils.Constant;

import java.util.Date;

@Entity(tableName = Constant.PASSBOOK_TABLE)
public abstract class PassBook extends BaseEntity {
    public String customerId;
    public Date creationDate;
    public int amount;
    public PassBookType passBookType;
    public PassbookState passbookState;
}
