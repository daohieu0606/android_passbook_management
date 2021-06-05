package com.example.passbook.data.entitys;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.passbook.utils.Constant;

@Entity(tableName = Constant.CUSTOMER_TABLE)
public class Customer extends BaseEntity {
    public String fullName;
    public String address;
    public String identifyNumber;
}
