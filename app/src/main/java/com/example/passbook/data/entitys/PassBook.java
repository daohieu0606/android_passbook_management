package com.example.passbook.data.entitys;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

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
public class PassBook extends BaseEntity {
    public int customerId;
    public Date creationDate;
    public int amount;
    public PassBookType passBookType;
    public PassbookState passbookState;

    public PassBook() {
        passbookState = PassbookState.OPENED;
    }
}
