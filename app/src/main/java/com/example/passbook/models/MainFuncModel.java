package com.example.passbook.models;

import com.example.passbook.utils.ApplicationFunction;

public class MainFuncModel extends BaseModel {
    public String iconFont;
    public String name;
    public ApplicationFunction applicationFunction;

    public MainFuncModel() {
    }

    public MainFuncModel(String iconFont, String name, ApplicationFunction applicationFunction) {
        this.iconFont = iconFont;
        this.name = name;
        this.applicationFunction = applicationFunction;
    }
}
