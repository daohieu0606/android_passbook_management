package com.example.passbook.daos;

import java.util.List;

public interface IDAO<T> {
    public List<T> getItems();
    public void insertItem(T item);
    public void updateOrInsertItem(T item);
    public T getItem(int id);
    public void deleteItem(T item);
}
