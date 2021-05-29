package com.example.passbook.datastores;

import java.util.List;

public interface IDataStore<T> {
    public List<T> getItems();
    public T getItem(String id);
    public boolean updateItem(T item);
    public boolean deleteItem(T item);
}
