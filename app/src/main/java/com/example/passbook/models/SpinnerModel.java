package com.example.passbook.models;

import com.example.passbook.utils.FormItemType;

public class SpinnerModel extends BaseFormModel {
    public SpinnerModel(String title, Object value, String hint) {
        super(title, value, hint);

        this.formItemType = FormItemType.Spinner;
    }
}
