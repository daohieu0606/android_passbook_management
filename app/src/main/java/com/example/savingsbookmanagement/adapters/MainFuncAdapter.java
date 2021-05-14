package com.example.savingsbookmanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savingsbookmanagement.R;
import com.example.savingsbookmanagement.customviews.IconLabel;
import com.example.savingsbookmanagement.models.MainFuncModel;

import java.util.List;

public class MainFuncAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<MainFuncModel> items;

    public MainFuncAdapter(Context context, List<MainFuncModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.adapter_main_function_button_item, null, false);

        MainFuncViewHolder viewHolder = new MainFuncViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MainFuncModel item = items.get(position);
        MainFuncViewHolder mainFuncViewHolder = (MainFuncViewHolder) holder;

        if(item == null || mainFuncViewHolder == null) {
            return;
        }

        mainFuncViewHolder.icDisplay.setText(item.iconFont);
        mainFuncViewHolder.txtName.setText(item.name);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class MainFuncViewHolder extends RecyclerView.ViewHolder{
        public IconLabel icDisplay;
        public TextView txtName;

        public MainFuncViewHolder(@NonNull View itemView) {
            super(itemView);

            icDisplay = itemView.findViewById(R.id.icDisplay);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}
