package com.example.passbook.models;

import com.example.passbook.utils.FormItemType;

import java.util.List;

public class SpinnerModel extends BaseFormModel {

    public List<String> items;

    public SpinnerModel(String title, Object value, String hint, List<String> items) {
        super(title, value, hint);
        this.items = items;
        this.formItemType = FormItemType.Spinner;
    }
}
