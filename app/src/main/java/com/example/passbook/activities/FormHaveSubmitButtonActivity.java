package com.example.passbook.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passbook.R;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.models.BaseFormModel;
import com.example.passbook.models.BaseModel;

import java.util.List;

public abstract class FormHaveSubmitButtonActivity extends BaseFormActivity {
    protected Button btnSubmit;
    protected RecyclerView lst_input;
    protected FormAdapter adapter;
    protected List<BaseFormModel> models;

    public FormHaveSubmitButtonActivity(){
        containerLayout = R.layout.activity_adapter_listview_button;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnSubmit = findViewById(R.id.btnSubmit);
        lst_input = findViewById(R.id.lst_input);

        lst_input.setAdapter(adapter);
        lst_input.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
    }
}
