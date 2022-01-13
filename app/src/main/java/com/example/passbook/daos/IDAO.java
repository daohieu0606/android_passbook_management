package com.example.passbook.daos;

import java.util.List;

public interface IDAO<T> {
    public List<T> getItems();
    public long insertItem(T item);
    public void updateOrInsertItem(T item);
    public T getItem(long id);
    public int deleteItem(T item);
    public void deleteAll();
}
