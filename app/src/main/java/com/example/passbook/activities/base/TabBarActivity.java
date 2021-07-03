package com.example.passbook.activities.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.passbook.R;
import com.example.passbook.activities.about.AboutActivity;
import com.example.passbook.customviews.CustomDialog;
import com.example.passbook.customviews.IconLabel;
import com.example.passbook.data.enums.LanguageType;
import com.example.passbook.data.enums.ThemeType;
import com.example.passbook.intefaces.OnDialogButtonClick;
import com.example.passbook.services.ICurrentStateService;
import com.example.passbook.services.ServiceLocator;
import com.example.passbook.utils.LocaleHelper;
import com.example.passbook.utils.ThemeHelper;

public abstract class TabBarActivity extends BaseActivity implements PopupMenu.OnMenuItemClickListener{
    protected int containerLayout = 0;

    private IconLabel btnBack;
    private TextView txtTitle;
    private IconLabel btnMenu;

    protected String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_form);
        Init();
    }

    private void Init() {
        btnBack = findViewById(R.id.btnBack);
        txtTitle = findViewById(R.id.txtTitle);
        btnMenu = findViewById(R.id.btnMenu);

        if(containerLayout != 0) {
            RelativeLayout container = findViewById(R.id.container);

            LayoutInflater layoutInflater = LayoutInflater.from(this);
            layoutInflater.inflate(containerLayout, container, true);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });

        txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(title != null && title != "") {
            txtTitle.setText(title);
        }
    }

    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.setOnMenuItemClickListener(this::onMenuItemClick);
        popup.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnLanguage:
                showChangeLanguageDialog();
                return true;

            case R.id.btnTheme:
                performChangeTheme();
                return true;

            case R.id.btnAbout:
                goToAboutActivity();
                return true;

            default:
                return false;
        }
    }

    private void goToAboutActivity() {
        Intent intent = new Intent(this, AboutActivity.class);
        moveToAnotherActivity(intent);
    }

    private void showChangeLanguageDialog() {
        CustomDialog customDialog = new CustomDialog(
                this,
                R.layout.body_select_language,
                getString(R.string.select_language));

        RadioGroup rgLanguage = customDialog.getBody().findViewById(R.id.rgLanguage);

        setSelectedLanguageRadioButton(rgLanguage);

        customDialog.onDialogButtonClick = new OnDialogButtonClick() {
            @Override
            public void onNegativeClick() {

            }

            @Override
            public void onPositiveClick() {
                performChangeLanguage(rgLanguage);
                customDialog.dismiss();
            }
        };

        customDialog.show();
    }

    private void setSelectedLanguageRadioButton(RadioGroup rgLanguage) {
        LanguageType languageType = currentStateService.getCurrentLanguageType();

        switch (languageType) {
            case VIETNAMESE:
                rgLanguage.check(R.id.rbVietnamese);
                break;

            case ENGLISH:
            default:
                rgLanguage.check(R.id.rbEnglish);
        }
    }

    private void performChangeLanguage(RadioGroup rgLanguage){
        int selectedId = rgLanguage.getCheckedRadioButtonId();

        switch (selectedId) {
            case R.id.rbVietnamese:
                LocaleHelper.setLocale(this, LanguageType.VIETNAMESE);
                break;

            case R.id.rbEnglish:
            default:
                LocaleHelper.setLocale(this, LanguageType.ENGLISH);
                break;
        }

        ThemeHelper.reloadActivity(this);
    }

    private void performChangeTheme() {
        CustomDialog customDialog = new CustomDialog(
                this,
                R.layout.body_select_theme,
                getString(R.string.select_theme));

        RadioGroup rgTheme = customDialog.getBody().findViewById(R.id.rgTheme);

        setSelectedThemeRadioButton(rgTheme);

        setOnThemeRadioGroupSelected(customDialog, rgTheme);

        customDialog.show();
    }

    private void setOnThemeRadioGroupSelected(CustomDialog customDialog, RadioGroup rgTheme) {
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
                        presenter.changeTheme(ThemeType.PINK);
                        break;

                    case R.id.rbBlue:
                        presenter.changeTheme(ThemeType.BLUE);
                        break;

                    case R.id.rbYellow:
                        presenter.changeTheme(ThemeType.YELLOW);
                        break;
                }
            }
        };
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
}