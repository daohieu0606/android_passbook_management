package com.example.passbook.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.passbook.utils.Constant;

import java.util.List;

@Dao
public interface CustomerDAO
        extends IDAO {
    @Override
    @Query(Constant.SELECT_ALL + Constant.CUSTOMER_TABLE)
    List getItems();

    @Override
    @Insert
    void insertItem(Object item);

    @Override
    @Update
    void updateOrInsertItem(Object item);

    @Override
    Object getItem(int id);     //TODO: handle this function

    @Override
    @Delete
    void deleteItem(Object item);
}
