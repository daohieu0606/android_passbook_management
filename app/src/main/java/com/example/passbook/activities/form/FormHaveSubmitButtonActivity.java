package com.example.passbook.activities.form;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passbook.R;
import com.example.passbook.activities.base.BaseActivity;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.adapters.SpacesItemDecoration;
import com.example.passbook.data.models.BaseFormModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public abstract class FormHaveSubmitButtonActivity
        extends BaseActivity
        implements FormContract.View {
    protected Button btnSubmit;
    protected RecyclerView lst_input;
    protected FormAdapter adapter;
    protected List<BaseFormModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        containerLayout = R.layout.activity_adapter_listview_button;

        super.onCreate(savedInstanceState);

        btnSubmit = findViewById(R.id.btnSubmit);
        lst_input = findViewById(R.id.lst_input);

        initModelAndAdapter();

        lst_input.setAdapter(adapter);
        lst_input.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        lst_input.addItemDecoration(new SpacesItemDecoration(20));

        btnSubmit.setOnClickListener(v -> HandleSubmit());
    }

    private void HandleSubmit(){
        resetValid();

        if(isValidData()) {
            getDataFromViewAndCallPresenterHandle();
        }

        adapter.notifyDataSetChanged();
    }

    protected boolean isValidData() {
        boolean result = true;

        for (BaseFormModel baseFormModel :
                models) {
            if(!baseFormModel.isValueEmpty()){
                result = false;
                baseFormModel.isError = true;
                baseFormModel.errorSTr =  getString(R.string.you_must_fill_out_this_field);
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

    protected abstract void getDataFromViewAndCallPresenterHandle();

    @Override
    public void handleSuccess() {
        showMessage(getString(R.string.handle_success));
        finish();
    }

    @Override
    public void handleFailed() {
        //showSnackBar(getString(R.string.handle_failed));
    }

    private void showMessage(String message) {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
