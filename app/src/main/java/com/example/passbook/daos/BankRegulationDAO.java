package com.example.passbook.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.utils.Constant;

import java.util.List;

@Dao
public interface BankRegulationDAO extends IDAO<BankRegulation> {
    @Override
    @Query(Constant.SELECT_ALL + Constant.BANK_REGULATION_TABLE)
    List<BankRegulation> getItems();

    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertItem(BankRegulation item);

    @Override
    @Update
    void updateOrInsertItem(BankRegulation item);

    @Override
    @Query(Constant.SELECT_ALL + Constant.BANK_REGULATION_TABLE
            + Constant.WHERE + Constant.ID + Constant.LIKE + "id"
            + Constant.LIMIT + "1")
    BankRegulation getItem(int id);

    @Override
    @Delete
    int deleteItem(BankRegulation item);

    @Override
    @Query(Constant.DELETE_ALL + Constant.BANK_REGULATION_TABLE)
    void deleteAll();
}
