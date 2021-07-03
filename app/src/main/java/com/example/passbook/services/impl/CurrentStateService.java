package com.example.passbook.services.impl;

import com.example.passbook.data.enums.ThemeType;
import com.example.passbook.services.ICurrentStateService;

public class CurrentStateService implements ICurrentStateService {
    private ThemeType currentTheme;

    @Override
    public ThemeType getCurrentThemeType() {
        return currentTheme;
    }

    @Override
    public void setCurrentTheme(ThemeType themeType) {
        this.currentTheme = themeType;
    }
}
