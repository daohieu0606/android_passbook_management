package com.example.passbook.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passbook.R;
import com.example.passbook.models.BaseFormModel;
import com.example.passbook.models.DateTimeModel;
import com.example.passbook.models.SpinnerModel;
import com.example.passbook.models.TextFieldModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class FormAdapter extends RecyclerView.Adapter {

    private List<BaseFormModel> items;
    private Activity activity;

    public FormAdapter(Activity activity, List<BaseFormModel> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType < 0 || viewType > items.size()) {
            return null;
        }

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        BaseViewHolder viewHolder = null;
        View view = null;

        switch (items.get(viewType).formItemType){
            case Spinner:
                view = inflater.inflate(R.layout.item_adapter_spinner, parent, false);
                viewHolder = new SpinnerViewHolder(view);
                break;

            case DateTime:
                view = inflater.inflate(R.layout.item_adapter_date_time, parent,false);
                viewHolder = new DatetimeViewHolder(view);
                break;

            case TextField:
                view = inflater.inflate(R.layout.item_adapter_text_field, parent, false);
                viewHolder = new TextFieldViewHolder(view);
                break;

            default:
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((BaseViewHolder)holder).position = position;

        switch (items.get(position).formItemType) {
            case DateTime:
                DatetimeViewHolder datetimeViewHolder = (DatetimeViewHolder) holder;
                DateTimeModel dateTimeModel = (DateTimeModel) items.get(position);

                datetimeViewHolder.txtLayout.setHint(dateTimeModel.title);
                datetimeViewHolder.txtValue.setText((String)dateTimeModel.value);

                break;

            case Spinner:
                SpinnerViewHolder spinnerViewHolder = (SpinnerViewHolder) holder;
                SpinnerModel spinnerModel = (SpinnerModel) items.get(position);

                ArrayAdapter adapter = new ArrayAdapter(activity, R.layout.support_simple_spinner_dropdown_item, (List<String>) spinnerModel.value);
                spinnerViewHolder.list_item.setAdapter(adapter);

                break;
            case TextField:
                TextFieldViewHolder textFieldViewHolder = (TextFieldViewHolder) holder;
                TextFieldModel textFieldModel = (TextFieldModel) items.get(position);

                textFieldViewHolder.textField.setHint(textFieldModel.title);
                textFieldViewHolder.textField.setError(textFieldModel.error);

                textFieldViewHolder.edt.setText((String)textFieldModel.value);
                textFieldViewHolder.edt.setInputType(textFieldModel.inputType);

                textFieldViewHolder.textField.setErrorEnabled(false);

                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public boolean validate(){
        return false;
    }

    public abstract class BaseViewHolder extends RecyclerView.ViewHolder{

        public int position;
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class TextFieldViewHolder extends BaseViewHolder {
        public TextInputLayout textField;
        public TextInputEditText edt;

        public TextFieldViewHolder(@NonNull View itemView) {
            super(itemView);

            textField = itemView.findViewById(R.id.textField);
            edt = itemView.findViewById(R.id.edt);
        }
    }

    public class DatetimeViewHolder extends BaseViewHolder {

        public TextInputLayout txtLayout;
        public TextInputEditText txtValue;

        public DatetimeViewHolder(@NonNull View itemView) {
            super(itemView);

            txtLayout = itemView.findViewById(R.id.txtLayout);
            txtValue = itemView.findViewById(R.id.txtValue);
        }
    }

    public class SpinnerViewHolder extends BaseViewHolder{

        public AutoCompleteTextView list_item;
        public SpinnerViewHolder(@NonNull View itemView) {
            super(itemView);

            list_item = itemView.findViewById(R.id.list_item);
        }
    }
}
