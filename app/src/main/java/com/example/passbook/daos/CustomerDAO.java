package com.example.passbook.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.passbook.data.entitys.Customer;
import com.example.passbook.utils.Constant;

import java.util.List;

@Dao
public interface CustomerDAO extends IDAO<Customer> {
    @Override
    @Query(Constant.SELECT_ALL + Constant.CUSTOMER_TABLE)
    List<Customer> getItems();

    @Override
    @Insert
    long insertItem(Customer item);

    @Override
    @Update
    void updateOrInsertItem(Customer item);

    @Override
    @Query(Constant.SELECT_ALL + Constant.CUSTOMER_TABLE
            + Constant.WHERE + Constant.ID + Constant.LIKE + "id"
            + Constant.LIMIT + "1")
    Customer getItem(int id);

    @Query(Constant.SELECT_ALL + Constant.CUSTOMER_TABLE
            + Constant.WHERE + Constant.IDENTIFY_NUMBER_COLUMN + Constant.LIKE + "identifyNumber"
            + Constant.LIMIT + "1")
    Customer getCustomerByIdentifyNumber(String identifyNumber);

    @Override
    @Delete
    int deleteItem(Customer item);

    @Override
    @Query(Constant.DELETE_ALL + Constant.CUSTOMER_TABLE)
    void deleteAll();
}
