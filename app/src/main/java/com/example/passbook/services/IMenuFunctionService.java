package com.example.passbook.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.passbook.data.enums.LanguageType;
import com.example.passbook.data.enums.ThemeType;

public interface IMenuFunctionService {
    void showChangeLanguageDialog(AppCompatActivity activity);
    void showChangeThemeDialog(AppCompatActivity activity);
    void goToAboutActivity(AppCompatActivity activity);
}
