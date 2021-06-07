package com.example.passbook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passbook.R;
import com.example.passbook.customviews.IconLabel;
import com.example.passbook.models.MainFuncModel;

import java.util.List;

public class MainFuncAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<MainFuncModel> items;
    private OnItemAdapterClickListener onItemAdapterClickListener;

    public MainFuncAdapter(Context context, List<MainFuncModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item_adapter_main_function_button, null, false);

        MainFuncViewHolder viewHolder = new MainFuncViewHolder(view);

        RelativeLayout bg = view.findViewById(R.id.bg);
        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemAdapterClickListener != null) {
                    onItemAdapterClickListener.OnItemClicked(items.get(viewHolder.position));
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MainFuncModel item = items.get(position);
        MainFuncViewHolder mainFuncViewHolder = (MainFuncViewHolder) holder;

        if(item == null || mainFuncViewHolder == null) {
            return;
        }

        mainFuncViewHolder.position = position;

        mainFuncViewHolder.icDisplay.setText(item.iconFont);
        mainFuncViewHolder.txtName.setText(item.name);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClick(OnItemAdapterClickListener onItemAdapterClickListener) {
        this.onItemAdapterClickListener = onItemAdapterClickListener;
    }

    public class MainFuncViewHolder extends RecyclerView.ViewHolder{
        public IconLabel icDisplay;
        public TextView txtName;

        public int position;

        public MainFuncViewHolder(@NonNull View itemView) {
            super(itemView);

            icDisplay = itemView.findViewById(R.id.icDisplay);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }

    public interface OnItemAdapterClickListener{
        void OnItemClicked(MainFuncModel item);
    }
}
