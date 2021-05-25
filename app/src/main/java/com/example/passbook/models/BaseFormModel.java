package com.example.passbook.models;

import com.example.passbook.utils.FormItemType;

public class BaseFormModel extends BaseModel {
    public String title;
    public Object value;
    public String hint;
    public FormItemType formItemType;

    public BaseFormModel(String title, Object value, String hint) {
        this.title = title;
        this.value = value;
        this.hint = hint;
    }
}
