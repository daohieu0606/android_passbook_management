package com.example.passbook.data.models;

import com.example.passbook.utils.FormItemType;

public class TextFieldModel extends BaseFormModel {
    public int inputType;

    public TextFieldModel(String title, Object value, String hint, int inputType) {
        super(title, value, hint);

        this.formItemType = FormItemType.TextField;

        this.inputType = inputType;
    }
}
