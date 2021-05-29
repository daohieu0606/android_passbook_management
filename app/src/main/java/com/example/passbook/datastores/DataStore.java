package com.example.passbook.datastores;

import java.util.List;

public class DataStore<T> implements IDataStore<T> {

    protected String tableName;

    @Override
    public List<T> getItems() {
        return null;
    }

    @Override
    public T getItem(String id) {
        return null;
    }

    @Override
    public boolean updateItem(T item) {
        return false;
    }

    @Override
    public boolean deleteItem(T item) {
        return false;
    }
}
