package com.example.savingsbookmanagement.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.savingsbookmanagement.R;

public abstract class BaseFormActivity extends AppCompatActivity {

    protected int containerLayout = 0;

    public BaseFormActivity(int containerLayout) {
        this.containerLayout = containerLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_form);

        if(containerLayout != 0) {
            RelativeLayout container = findViewById(R.id.container);

            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            layoutInflater.inflate(containerLayout, container, true);
        }
    }
}