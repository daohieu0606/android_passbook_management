package com.example.passbook.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passbook.R;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.adapters.SpacesItemDecoration;
import com.example.passbook.models.BaseFormModel;

import java.util.List;

public abstract class FormHaveSubmitButtonActivity extends BaseActivity {
    protected Button btnSubmit;
    protected RecyclerView lst_input;
    protected FormAdapter adapter;
    protected List<BaseFormModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        containerLayout = R.layout.activity_adapter_listview_button;

        initModelAndAdapter();

        btnSubmit = findViewById(R.id.btnSubmit);
        lst_input = findViewById(R.id.lst_input);

        lst_input.setAdapter(adapter);
        lst_input.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        lst_input.addItemDecoration(new SpacesItemDecoration(20));

        btnSubmit.setOnClickListener(v -> HandleSubmit());
    }

    protected boolean isValidData() {
        boolean result = true;

        for (BaseFormModel baseFormModel :
                models) {
            if(!baseFormModel.isValueEmpty()){
                result = false;
                baseFormModel.isError = true;
            }
        }

        return result;
    }

    protected void resetValid() {
        for (BaseFormModel baseFormModel :
                models) {
            baseFormModel.isError = false;
        }
    }

    protected abstract void initModelAndAdapter();
    protected abstract void HandleSubmit();
}
