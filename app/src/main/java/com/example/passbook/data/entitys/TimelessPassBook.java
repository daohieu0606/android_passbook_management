package com.example.passbook.data.entitys;

public class TimelessPassBook extends PassBook {
    @Override
    public void deposit(int amount) {
        this.amount += amount;
    }
}
