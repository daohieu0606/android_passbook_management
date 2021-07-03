package com.example.passbook.services.impl;

import com.example.passbook.data.enums.LanguageType;
import com.example.passbook.data.enums.ThemeType;
import com.example.passbook.services.ICurrentStateService;

public class CurrentStateService implements ICurrentStateService {
    private ThemeType currentTheme;
    private LanguageType currentLanguage;

    @Override
    public ThemeType getCurrentThemeType() {
        return currentTheme;
    }

    @Override
    public void setCurrentTheme(ThemeType themeType) {
        this.currentTheme = themeType;
    }

    @Override
    public LanguageType getCurrentLanguageType() {
        return currentLanguage;
    }

    @Override
    public void setCurrentLanguageType(LanguageType languageType) {
        this.currentLanguage = languageType;
    }
}
