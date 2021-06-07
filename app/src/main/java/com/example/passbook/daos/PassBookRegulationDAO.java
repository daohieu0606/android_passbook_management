package com.example.passbook.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.passbook.data.entitys.PassBookRegulation;
import com.example.passbook.utils.Constant;

import java.util.List;

@Dao
public interface PassBookRegulationDAO extends IDAO<PassBookRegulation> {

    @Override
    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_REGULATION_TABLE)
    List<PassBookRegulation> getItems();

    @Override
    @Insert
    void insertItem(PassBookRegulation item);

    @Override
    @Update
    void updateOrInsertItem(PassBookRegulation item);

    @Override
    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_REGULATION_TABLE
            + Constant.WHERE + Constant.ID + Constant.LIKE + "id"
            + Constant.LIMIT + "1")
    PassBookRegulation getItem(int id);

    @Override
    @Delete
    void deleteItem(PassBookRegulation item);

    @Override
    @Query(Constant.DELETE_ALL + Constant.PASSBOOK_REGULATION_TABLE)
    void deleteAll();
}
