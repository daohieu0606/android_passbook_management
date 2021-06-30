package com.example.passbook.customviews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passbook.R;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.adapters.SpacesItemDecoration;
import com.example.passbook.data.models.BaseFormModel;

import java.util.List;

public class DialogHaveListView extends CustomDialog {
    private RecyclerView lst_input;
    private List<BaseFormModel> models;
    private FormAdapter adapter;

    public DialogHaveListView(@NonNull AppCompatActivity activity, @NonNull List<BaseFormModel> models, String title) {
        super(activity, R.layout.view_contain_only_listview, title);
        lst_input = body.findViewById(R.id.lst_input);

        this.activity = activity;
        this.models = models;

        adapter = new FormAdapter(activity, models, lst_input);

        lst_input.setAdapter(adapter);
        lst_input.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext()));
        lst_input.addItemDecoration(new SpacesItemDecoration(20));
    }

    @Override
    protected void handleYesButton() {
        resetValid();
        if(isValidData()) {
            super.handleYesButton();
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

    public void notifyDataChanged(){
        adapter.notifyDataSetChanged();
    }
}
