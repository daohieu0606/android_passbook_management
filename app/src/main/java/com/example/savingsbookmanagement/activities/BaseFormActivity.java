package com.example.savingsbookmanagement.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.savingsbookmanagement.R;
import com.example.savingsbookmanagement.customviews.IconLabel;

public abstract class BaseFormActivity extends AppCompatActivity {

    protected int containerLayout = 0;

    private IconLabel btnBack;
    private IconLabel txtTitle;
    private IconLabel btnMenu;

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_form);

        Intent intent = getIntent();

        if(intent != null) {
            containerLayout = intent.getIntExtra("layout", 0);
            title = intent.getStringExtra("title");
        }

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
                //TODO: create menu
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
}