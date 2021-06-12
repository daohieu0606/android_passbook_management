package com.example.passbook.models;

import android.text.InputType;

import com.example.passbook.utils.FormItemType;

public class TextFieldModel extends BaseFormModel {
    public int inputType;

    public TextFieldModel(String title, Object value, String hint, int inputType) {
        super(title, value, hint);

        this.formItemType = FormItemType.TextField;

        this.inputType = inputType;
    }
}
