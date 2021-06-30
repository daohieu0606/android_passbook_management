package com.example.passbook.data.models;

import com.example.passbook.utils.FormItemType;

import org.apache.commons.lang3.StringUtils;

public class BaseFormModel extends BaseModel {
    public String title;
    public Object value;
    public String hint;
    public FormItemType formItemType;
    public boolean isError;
    public String errorSTr;
    public boolean isEnable;

    public BaseFormModel(String title, Object value, String hint) {
        this.title = title;
        this.value = value;
        this.hint = hint;
        isError = false;
        isEnable = true;
    }

    public boolean isValueEmpty() {
        if (value == null || StringUtils.isEmpty(value.toString())){
            return false;
        } else {
            errorSTr = null;
            return true;
        }
    }
}
