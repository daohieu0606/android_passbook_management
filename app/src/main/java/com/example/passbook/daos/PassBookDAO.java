package com.example.passbook.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.utils.Constant;

import java.util.Date;
import java.util.List;

@Dao
public interface PassBookDAO extends IDAO<PassBook> {
    @Override
    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_TABLE)
    List<PassBook> getItems();

    @Override
    @Insert
    long insertItem(PassBook item);

    @Override
    @Update
    void updateOrInsertItem(PassBook item);

    @Override
    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_TABLE
            + Constant.WHERE + Constant.ID + Constant.LIKE + "id"
            + Constant.LIMIT + "1")
    PassBook getItem(int id);

    @Override
    @Delete
    int deleteItem(PassBook item);

    @Override
    @Query(Constant.DELETE_ALL + Constant.PASSBOOK_TABLE)
    void deleteAll();

    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_TABLE
            + Constant.ORDER_BY +"Id"
            + Constant.DESC
            + Constant.LIMIT +"1")
    PassBook getLastItem();

    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_TABLE
            + Constant.WHERE + Constant.CREATION_DATE_COLUMN + " BETWEEN :from AND :to")
    List<PassBook> getPassBooksByDate(long from, long to);
}
