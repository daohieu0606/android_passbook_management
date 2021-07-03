package com.example.passbook.data.enums;

import com.example.passbook.utils.Constant;

public enum  ThemeType {
    DEFAULT,
    BLUE,
    PINK,
    YELLOW;

    public static String toTextString(ThemeType themeType) {
        String stringTheme = "";

        switch (themeType) {
            case BLUE:
                stringTheme = Constant.BLUE_THEME;
                break;

            case PINK:
                stringTheme = Constant.PINK_THEME;
                break;

            case YELLOW:
                stringTheme = Constant.YELLOW_THEME;
                break;

            case DEFAULT:
                stringTheme = Constant.DEFAULT_THEME;
        }

        return stringTheme;
    }

    public static ThemeType fromTextString(String themeTypeStr) {
        ThemeType themeType = DEFAULT;

        switch (themeTypeStr) {
            case Constant.BLUE_THEME:
                themeType = BLUE;
                break;

            case Constant.PINK_THEME:
                themeType = PINK;
                break;

            case Constant.YELLOW_THEME:
                themeType = YELLOW;
                break;

            default:
                themeType = DEFAULT;
        }

        return themeType;
    }
}
