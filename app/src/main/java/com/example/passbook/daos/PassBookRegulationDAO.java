package com.example.passbook.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.passbook.data.entitys.PassBookRegulation;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.utils.Constant;

import java.util.List;

@Dao
public interface PassBookRegulationDAO extends IDAO<PassBookRegulation> {

    @Override
    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_REGULATION_TABLE)
    List<PassBookRegulation> getItems();

    @Override
    @Insert
    long insertItem(PassBookRegulation item);

    @Override
    @Update
    void updateOrInsertItem(PassBookRegulation item);

    @Override
    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_REGULATION_TABLE
            + Constant.WHERE + Constant.ID + Constant.LIKE + "id"
            + Constant.LIMIT + "1")
    PassBookRegulation getItem(long id);

    @Override
    @Delete
    int deleteItem(PassBookRegulation item);

    @Override
    @Query(Constant.DELETE_ALL + Constant.PASSBOOK_REGULATION_TABLE)
    void deleteAll();

    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_REGULATION_TABLE
            + Constant.WHERE + Constant.PASSBOOK_TYPE_COLUMN + Constant.LIKE + "passBookType"
            + Constant.ORDER_BY + Constant.CREATION_DATE_TIME_COLUMN + Constant.DESC
            + Constant.LIMIT + "1")
    PassBookRegulation getLastPassBookByType(PassBookType passBookType);

    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_REGULATION_TABLE
            + Constant.WHERE + Constant.PASSBOOK_TYPE_COLUMN + Constant.LIKE + "passBookType")
    List<PassBookRegulation> getItemsByType(PassBookType passBookType);

    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_REGULATION_TABLE
            + Constant.WHERE + Constant.PASSBOOK_TYPE_COLUMN + Constant.LIKE + "passBookType"
            + Constant.AND + Constant.CREATION_DATE_TIME_COLUMN + Constant.SMALLER_OR_EQUAL + "beginDate"
            + Constant.ORDER_BY + Constant.CREATION_DATE_TIME_COLUMN + Constant.DESC + Constant.LIMIT + "1")
    PassBookRegulation getItemForCalInterestRate(PassBookType passBookType, long beginDate);
}
