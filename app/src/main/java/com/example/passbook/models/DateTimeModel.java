package com.example.passbook.models;

import com.example.passbook.utils.FormItemType;

import java.util.Date;

public class DateTimeModel extends BaseFormModel {
    public DateTimeModel(String title, Object value, String hint) {
        super(title, value, hint);

        if(value == null) {
            this.value = new Date();
        }

        this.formItemType = FormItemType.DateTime;
    }
}
