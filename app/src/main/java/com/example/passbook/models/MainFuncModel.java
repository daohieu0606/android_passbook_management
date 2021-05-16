package com.example.passbook.models;

public class MainFuncModel extends BaseModel {
    public String iconFont;
    public String name;

    public MainFuncModel() {
    }

    public MainFuncModel(String iconFont, String name) {
        this.iconFont = iconFont;
        this.name = name;
    }
}

enum ApplicationFunction {
    CREATE_SAVING_BOOK,
}
