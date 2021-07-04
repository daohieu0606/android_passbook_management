package com.example.passbook.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passbook.R;
import com.example.passbook.activities.searchpassbook.SearchPassBookActivity;
import com.example.passbook.data.models.PassBookItem;

import java.util.List;

public class SearchPassBookAdapter extends RecyclerView.Adapter<SearchPassBookAdapter.SearchPassBookViewHolder> {
    private SearchPassBookActivity activity;
    private List<PassBookItem> models;

    public SearchPassBookAdapter(SearchPassBookActivity activity, List<PassBookItem> models) {
        this.activity = activity;
        this.models = models;
    }

    public void setModels(List<PassBookItem> anotherModels) {
        this.models = anotherModels;
    }

    @NonNull
    @Override
    public SearchPassBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.item_lv_passbook, parent, false);

        SearchPassBookViewHolder viewHolder = new SearchPassBookViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchPassBookAdapter.SearchPassBookViewHolder holder, int position) {
        PassBookItem passBookItem = models.get(position);
        if (passBookItem != null) {
            holder.txtPassbookType.setText(passBookItem.passBookType);
            holder.txtCustomerName.setText(passBookItem.customerName);
            holder.txtPassBookId.setText(passBookItem.passBookId);
            holder.txtAmount.setText(passBookItem.amount);

            holder.item_passbook.setOnLongClickListener(v -> {
                PopupMenu popup = new PopupMenu(activity, holder.rlMenu);
                popup.setOnMenuItemClickListener(activity);
                popup.inflate(R.menu.search_passbook_menu);
                popup.show();
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class SearchPassBookViewHolder extends RecyclerView.ViewHolder{
        private TextView txtPassbookType;
        private TextView txtCustomerName;
        private TextView txtPassBookId;
        private TextView txtAmount;

        public RelativeLayout item_passbook;
        public RelativeLayout rlMenu;

        public SearchPassBookViewHolder(@NonNull View itemView) {
            super(itemView);

            txtPassbookType = itemView.findViewById(R.id.txtPassbookType);
            txtCustomerName = itemView.findViewById(R.id.txtCustomerName);
            txtPassBookId = itemView.findViewById(R.id.txtPassBookId);
            txtAmount = itemView.findViewById(R.id.txtAmount);

            item_passbook = itemView.findViewById(R.id.item_passbook);
            rlMenu = itemView.findViewById(R.id.rlMenu);
        }
    }
}
