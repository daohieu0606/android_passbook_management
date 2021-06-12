package com.example.passbook.data.entitys;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.example.passbook.data.enums.TransactionFormType;
import com.example.passbook.utils.Constant;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = Constant.TRANSACTION_FORM_TABLE,
        foreignKeys = {@ForeignKey(entity = PassBook.class,
                                    parentColumns = "id",
                                    childColumns = "passBookId",
                                    onDelete = CASCADE),
                        @ForeignKey( entity = Customer.class,
                                parentColumns = "id",
                                childColumns = "customerId",
                                onDelete = CASCADE)
                        })
public class TransactionForm extends BaseEntity {
    public int passBookId;
    public int customerId;
    public Date transactionDateTime;
    public int amount;
    public TransactionFormType transactionFormType;
}
