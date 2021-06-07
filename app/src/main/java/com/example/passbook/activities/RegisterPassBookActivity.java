package com.example.passbook.activities;

import android.os.Bundle;

import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.utils.Constant;

import java.util.ArrayList;

public class RegisterPassBookActivity extends FormHaveSubmitButtonActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        models = new ArrayList<>();
        adapter = new FormAdapter(this, models);

        title = Constant.REGISTER_PASSBOOK;

        super.onCreate(savedInstanceState);
    }
}
