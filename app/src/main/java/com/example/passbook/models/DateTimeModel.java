package com.example.passbook.models;

import com.example.passbook.utils.FormItemType;

public class DateTimeModel extends BaseFormModel {
    public DateTimeModel(String title, Object value, String hint) {
        super(title, value, hint);

        this.formItemType = FormItemType.DateTime;
    }
}
