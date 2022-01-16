package com.example.passbook.data.entitys;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.enums.PassbookState;
import com.example.passbook.utils.Constant;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = Constant.PASSBOOK_TABLE,
        foreignKeys = @ForeignKey(entity = Customer.class,
        parentColumns = "id",
        childColumns = "customerId",
        onDelete = CASCADE))
public abstract class PassBook extends BaseEntity {
    public int customerId;
    public Date creationPassBookDate;
    public Date expiredDate;
    public int amount;
    public PassBookType passBookType;
    public PassbookState passbookState;

    //Hieu Dao add missing fields
    public float interestRate;

    public PassBook() {
        passbookState = PassbookState.OPENED;
    }

    public void deposit(int amount) {

    }
}
