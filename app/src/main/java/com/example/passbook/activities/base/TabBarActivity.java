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
import com.example.passbook.services.IMenuFunctionService;
import com.example.passbook.services.ServiceLocator;
import com.example.passbook.utils.LocaleHelper;
import com.example.passbook.utils.ThemeHelper;

public abstract class TabBarActivity extends BaseActivity implements PopupMenu.OnMenuItemClickListener{
    protected int containerLayout = 0;

    private IconLabel btnBack;
    private TextView txtTitle;
    private IconLabel btnMenu;

    protected String title;
    protected IMenuFunctionService menuFunctionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menuFunctionService = ServiceLocator.getInstance().getService(IMenuFunctionService.class);

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
                menuFunctionService.showChangeLanguageDialog(this);
                return true;

            case R.id.btnTheme:
                menuFunctionService.showChangeThemeDialog(this);
                return true;

            case R.id.btnAbout:
                menuFunctionService.goToAboutActivity(this);
                return true;

            default:
                return false;
        }
    }
}