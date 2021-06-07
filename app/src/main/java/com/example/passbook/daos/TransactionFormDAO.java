package com.example.passbook.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.passbook.data.entitys.TransactionForm;
import com.example.passbook.utils.Constant;

import java.util.List;

@Dao
public interface TransactionFormDAO extends IDAO<TransactionForm> {
    @Override
    @Query(Constant.SELECT_ALL + Constant.TRANSACTION_FORM_TABLE)
    List<TransactionForm> getItems();

    @Override
    @Insert
    void insertItem(TransactionForm item);

    @Override
    @Update
    void updateOrInsertItem(TransactionForm item);

    @Override
    @Query(Constant.SELECT_ALL + Constant.TRANSACTION_FORM_TABLE
            + Constant.WHERE + Constant.ID + Constant.LIKE + "id"
            + Constant.LIMIT + "1")
    TransactionForm getItem(int id);

    @Override
    @Delete
    void deleteItem(TransactionForm item);

    @Override
    @Query(Constant.DELETE_ALL + Constant.TRANSACTION_FORM_TABLE)
    void deleteAll();
}
