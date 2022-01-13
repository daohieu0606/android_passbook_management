package com.example.passbook.data.entitys;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class BaseEntity implements Serializable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long Id;

    //Hieu Dao add missing fields;
    public Date creationDateTime;
    public Date updateDateTime;
    public boolean isDeleted;
}
