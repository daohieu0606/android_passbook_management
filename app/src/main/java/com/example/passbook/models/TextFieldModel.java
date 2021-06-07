package com.example.passbook.models;

import android.text.InputType;

import com.example.passbook.utils.FormItemType;

public class TextFieldModel extends BaseFormModel {

    public int inputType;
    public String error;

    public TextFieldModel(String title, Object value, String hint, String error, int inputType) {
        super(title, value, hint);

        this.formItemType = FormItemType.TextField;

        this.inputType = inputType;
    }
}
