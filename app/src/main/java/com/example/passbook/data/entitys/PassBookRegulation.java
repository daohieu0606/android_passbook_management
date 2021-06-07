package com.example.passbook.data.entitys;

import androidx.room.Entity;

import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.utils.Constant;

import java.util.ConcurrentModificationException;
import java.util.Date;

@Entity(tableName = Constant.PASSBOOK_REGULATION_TABLE)
public class PassBookRegulation extends BaseEntity {
    public PassBookType passBookType;
    public Date creationDateTime;
    public int term;
    public float interestRate;
}
