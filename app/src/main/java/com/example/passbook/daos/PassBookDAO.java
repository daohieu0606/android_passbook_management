package com.example.passbook.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.utils.Constant;

import java.util.Date;
import java.util.List;

@Dao
public interface PassBookDAO extends IDAO<PassBook> {
    @Override
    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_TABLE)
    List<PassBook> getItems();

    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertItem(PassBook item);

    @Override
    @Update
    void updateOrInsertItem(PassBook item);

    @Override
    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_TABLE
            + Constant.WHERE + Constant.ID + Constant.LIKE + "id"
            + Constant.LIMIT + "1")
    PassBook getItem(long id);

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

    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_TABLE
            + Constant.WHERE + "(" + Constant.CREATION_DATE_COLUMN + " BETWEEN :from AND :to)"
            + Constant.AND + Constant.PASSBOOK_TYPE_COLUMN + Constant.LIKE + "passBookType")
    List<PassBook> getPassBooksByDateAndType(long from, long to, PassBookType passBookType);

    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_TABLE
            + Constant.WHERE + Constant.ID + Constant.LIKE + "id")
    List<PassBook> getItemsById(String id);

    @Query(Constant.SELECT_ALL + Constant.PASSBOOK_TABLE
            + Constant.WHERE + Constant.CUSTOMER_ID_COLUMN + Constant.LIKE + "customerId"
            + Constant.LIMIT + "1")
    PassBook getItemByCustomerId(long customerId);
}
