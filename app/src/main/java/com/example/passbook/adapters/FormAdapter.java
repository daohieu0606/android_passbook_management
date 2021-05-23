package com.example.passbook.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passbook.models.BaseFormModel;

import java.util.List;

public class FormAdapter extends RecyclerView.Adapter {

    private List<BaseFormModel> items;
    private Context context;

    public FormAdapter(Context context, List<BaseFormModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public boolean validate(){
        return false;
    }
}
