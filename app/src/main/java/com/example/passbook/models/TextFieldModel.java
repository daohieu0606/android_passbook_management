package com.example.passbook.models;

import android.text.InputType;

public class TextFieldModel extends BaseFormModel {

    public InputType inputType;

    public TextFieldModel(String title, Object value, String hint, InputType inputType) {
        super(title, value, hint);

        this.inputType = inputType;
    }
}
