package com.example.passbook.data.enums;

public enum PassBookType {
    THREE_MONTH(1),
    SIX_MONTH(2),
    INFINITE(4);

    private int value;

    PassBookType(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}