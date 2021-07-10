package com.example.passbook.services.impl;

import android.content.Intent;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.passbook.R;
import com.example.passbook.activities.about.AboutActivity;
import com.example.passbook.customviews.CustomDialog;
import com.example.passbook.data.enums.LanguageType;
import com.example.passbook.data.enums.ThemeType;
import com.example.passbook.intefaces.OnDialogButtonClick;
import com.example.passbook.services.IMenuFunctionService;
import com.example.passbook.services.ICurrentStateService;
import com.example.passbook.services.ServiceLocator;
import com.example.passbook.utils.LocaleHelper;
import com.example.passbook.utils.ThemeHelper;

public class MenuFunctionService implements IMenuFunctionService {
    @Override
    public void showChangeLanguageDialog(AppCompatActivity activity) {
        CustomDialog customDialog = new CustomDialog(
                activity,
                R.layout.body_select_language,
                activity.getString(R.string.select_language));

        RadioGroup rgLanguage = customDialog.getBody().findViewById(R.id.rgLanguage);

        setSelectedLanguageRadioButton(rgLanguage);

        customDialog.onDialogButtonClick = new OnDialogButtonClick() {
            @Override
            public void onNegativeClick() {

            }

            @Override
            public void onPositiveClick() {
                performChangeLanguage(rgLanguage, activity);
                customDialog.dismiss();
            }
        };

        customDialog.show();
    }

    private void setSelectedLanguageRadioButton(RadioGroup rgLanguage) {
        LanguageType languageType = ServiceLocator.getInstance()
                                    .getService(ICurrentStateService.class)
                                    .getCurrentLanguageType();
        switch (languageType) {
            case VIETNAMESE:
                rgLanguage.check(R.id.rbVietnamese);
                break;

            case ENGLISH:
            default:
                rgLanguage.check(R.id.rbEnglish);
        }
    }

    private void performChangeLanguage(RadioGroup rgLanguage, AppCompatActivity activity){
        int selectedId = rgLanguage.getCheckedRadioButtonId();

        switch (selectedId) {
            case R.id.rbVietnamese:
                LocaleHelper.setLocale(activity, LanguageType.VIETNAMESE);
                break;

            case R.id.rbEnglish:
            default:
                LocaleHelper.setLocale(activity, LanguageType.ENGLISH);
                break;
        }

        ThemeHelper.reloadActivity(activity);
    }

    @Override
    public void showChangeThemeDialog(AppCompatActivity activity) {
        CustomDialog customDialog = new CustomDialog(
                activity,
                R.layout.body_select_theme,
                activity.getString(R.string.select_theme));

        RadioGroup rgTheme = customDialog.getBody().findViewById(R.id.rgTheme);

        setSelectedThemeRadioButton(rgTheme);

        setOnThemeRadioGroupSelected(customDialog, rgTheme, activity);

        customDialog.show();
    }

    @Override
    public void goToAboutActivity(AppCompatActivity activity) {
        Intent intent = new Intent(activity, AboutActivity.class);
        if(intent != null){
            activity.startActivity(intent);
        }
    }

    private void setSelectedThemeRadioButton(RadioGroup rgTheme) {
        ThemeType themeType = ServiceLocator.getInstance()
                .getService(ICurrentStateService.class)
                .getCurrentThemeType();
        switch (themeType) {
            case PINK:
                rgTheme.check(R.id.rbPink);
                break;

            case BLUE:
                rgTheme.check(R.id.rbBlue);
                break;

            case YELLOW:
                rgTheme.check(R.id.rbYellow);
                break;

            case DEFAULT:
        }
    }

    private void setOnThemeRadioGroupSelected(CustomDialog customDialog, RadioGroup rgTheme, AppCompatActivity activity) {
        customDialog.onDialogButtonClick = new OnDialogButtonClick() {
            @Override
            public void onNegativeClick() {
                //do nothing
            }

            @Override
            public void onPositiveClick() {
                int selectedId = rgTheme.getCheckedRadioButtonId();

                switch (selectedId) {
                    case R.id.rbPink:
                        changeTheme(ThemeType.PINK, activity);
                        break;

                    case R.id.rbBlue:
                        changeTheme(ThemeType.BLUE, activity);
                        break;

                    case R.id.rbYellow:
                        changeTheme(ThemeType.YELLOW, activity);
                        break;
                }
            }
        };
    }

    public void changeTheme(ThemeType themeType, AppCompatActivity activity) {
        ThemeHelper.changeTheme(activity, themeType);
    }
}
