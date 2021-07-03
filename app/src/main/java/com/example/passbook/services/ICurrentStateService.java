package com.example.passbook.services;

import com.example.passbook.data.enums.LanguageType;
import com.example.passbook.data.enums.ThemeType;

public interface ICurrentStateService {
    ThemeType getCurrentThemeType();
    void setCurrentTheme(ThemeType themeType);

    LanguageType getCurrentLanguageType();
    void setCurrentLanguageType(LanguageType languageType);
}
