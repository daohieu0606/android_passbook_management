package com.example.passbook.data.enums;

import com.example.passbook.data.entitys.InfinitePassBook;
import com.example.passbook.data.entitys.SixMonthPassBook;
import com.example.passbook.data.entitys.ThreeMonthPassBook;
import com.example.passbook.utils.Constant;

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
    public String getText() {
        switch (value) {
            case 1:
                return Constant.THREE_MONTHS;

            case 2:
                return Constant.SIX_MONTHS;

            case 4:
            default:
                return Constant.INFINITE;
        }
    }

    public static PassBookType fromString(String value) {
        switch (value) {
            case Constant.THREE_MONTHS:
                return PassBookType.THREE_MONTH;

            case Constant.SIX_MONTHS:
                return PassBookType.SIX_MONTH;

            case Constant.INFINITE:
            default:
                return PassBookType.INFINITE;
        }
    }
}