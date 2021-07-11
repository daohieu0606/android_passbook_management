package com.example.passbook.data.enums;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passbook.R;
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

    public String getText(AppCompatActivity activity) {
        switch (value) {
            case 1:
                return activity.getString(R.string.three_months_type);

            case 2:
                return activity.getString(R.string.six_month_types);

            case 4:
            default:
                return activity.getString(R.string.infinite_type);
        }
    }

    public static PassBookType fromString(String value, AppCompatActivity activity) {
        if (value.equals(activity.getString(R.string.three_months_type))) {
            return THREE_MONTH;
        } else if (value.equals(activity.getString(R.string.six_month_types))) {
            return SIX_MONTH;
        } else {
            return INFINITE;
        }
    }
}