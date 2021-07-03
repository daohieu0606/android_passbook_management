package com.example.passbook.data.enums;

import com.example.passbook.utils.Constant;

public enum  LanguageType {
    ENGLISH,
    VIETNAMESE;

    @Override
    public String toString(){
        String result = "";

        if(this == ENGLISH) {
            result = Constant.ENGLISH_TYPE_STRING;
        } else {
            result = Constant.VIETNAMESE_TYPE_STRING;
        }

        return result;
    }

    public static LanguageType fromString(String languageStr) {
        LanguageType result = null;

        switch (languageStr) {
            case Constant.VIETNAMESE_TYPE_STRING:
                result = VIETNAMESE;
                break;

            case Constant.ENGLISH_TYPE_STRING:
            default:
                result = ENGLISH;
        }

        return result;
    }
}
