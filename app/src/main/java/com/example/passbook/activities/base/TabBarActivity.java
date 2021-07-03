package com.example.passbook.activities.base;

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
import com.example.passbook.customviews.CustomDialog;
import com.example.passbook.customviews.IconLabel;
import com.example.passbook.data.enums.ThemeType;
import com.example.passbook.intefaces.OnDialogButtonClick;
import com.example.passbook.services.ICurrentStateService;
import com.example.passbook.services.ServiceLocator;

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
                //TODO: handle change language
                return true;
            case R.id.btnTheme:
                performChangeTheme();
                return true;

            case R.id.btnAbout:
                //TODO: handle show about activity
                return true;
            default:
                return false;
        }
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
        ThemeType themeType = ServiceLocator.getInstance().getService(ICurrentStateService.class).getCurrentThemeType();
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