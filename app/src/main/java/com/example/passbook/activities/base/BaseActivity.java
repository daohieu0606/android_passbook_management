package com.example.passbook.activities.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.passbook.R;
import com.example.passbook.customviews.IconLabel;
import com.example.passbook.data.enums.ThemeType;
import com.example.passbook.services.AppDatabase;
import com.example.passbook.utils.Constant;
import com.example.passbook.utils.ThemeExtension;

import org.apache.commons.lang3.StringUtils;

public abstract class BaseActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener,
BaseContract.View{

    protected int containerLayout = 0;

    private IconLabel btnBack;
    private TextView txtTitle;
    private IconLabel btnMenu;

    protected String title;

    protected AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeExtension.setTheme(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_form);

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        Init();
    }

    private void Init() {
        btnBack = findViewById(R.id.btnBack);
        txtTitle = findViewById(R.id.txtTitle);
        btnMenu = findViewById(R.id.btnMenu);

        if(containerLayout != 0) {
            RelativeLayout container = findViewById(R.id.container);

            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
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
            case R.id.btnColor:
                ThemeExtension.changeTheme(this, ThemeType.BLUE);
                return true;
            case R.id.btnAbout:
                //TODO: handle show about activity
                return true;
            default:
                return false;
        }
    }

    @Override
    public void moveToAnotherActivity(Intent intent) {
        if(intent != null){
            this.startActivity(intent);
        }
    }
}