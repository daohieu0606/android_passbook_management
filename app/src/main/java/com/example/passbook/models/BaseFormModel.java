package com.example.passbook.models;

import com.example.passbook.utils.FormItemType;

public class BaseFormModel extends BaseModel {
    public String title;
    public Object value;
    public String hint;
    public FormItemType formItemType;
    public boolean isError;
    public String errorSTr;

    public BaseFormModel(String title, Object value, String hint) {
        this.title = title;
        this.value = value;
        this.hint = hint;
        isError = false;
    }

    public boolean isValueEmpty() {
        if (value == null || value == ""){
            errorSTr = "not empty";
            return false;
        } else {
            errorSTr = null;
            return true;
        }
    }
}
