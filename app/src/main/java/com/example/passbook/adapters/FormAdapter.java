package com.example.passbook.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passbook.R;
import com.example.passbook.customviews.MonthYearPickerDialog;
import com.example.passbook.data.enums.DatePickerType;
import com.example.passbook.data.models.BaseFormModel;
import com.example.passbook.data.models.DateTimeModel;
import com.example.passbook.data.models.SpinnerModel;
import com.example.passbook.data.models.TextFieldModel;
import com.example.passbook.intefaces.OnFormItemChangeListener;
import com.example.passbook.intefaces.OnItemAdapterChangeListener;
import com.example.passbook.intefaces.OnValueChanged;
import com.example.passbook.utils.Constant;
import com.example.passbook.utils.Utils;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class FormAdapter extends RecyclerView.Adapter {

    private List<BaseFormModel> items;
    private AppCompatActivity activity;
    private OnItemAdapterChangeListener onAdapterChangeListener;
    private OnItemAdapterChangeListener onClientListener;

    public FormAdapter(AppCompatActivity activity, List<BaseFormModel> items) {
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
                viewHolder = new DatetimeViewHolder(view, activity);

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

                String datePattern = dateTimeModel.datePickerType == DatePickerType.MONTH_YEAR_ONLY?
                                        Constant.MONTH_YEAR_FORMAT: Constant.SHORT_DATE;
                String strDate = Utils.dateToString((Date) dateTimeModel.value, datePattern);

                datetimeViewHolder.txtLayout.setHint(dateTimeModel.title);
                datetimeViewHolder.txtValue.setText(strDate);
                datetimeViewHolder.datePickerType = dateTimeModel.datePickerType;

                if(dateTimeModel.isError) {
                    datetimeViewHolder.txtLayout.setError(dateTimeModel.errorSTr);
                    datetimeViewHolder.txtLayout.setErrorEnabled(true);
                } else {
                    datetimeViewHolder.txtLayout.setErrorEnabled(false);
                }

                break;

            case Spinner:
                SpinnerViewHolder spinnerViewHolder = (SpinnerViewHolder) holder;
                SpinnerModel spinnerModel = (SpinnerModel) items.get(position);

                ArrayAdapter adapter = new ArrayAdapter(activity, R.layout.support_simple_spinner_dropdown_item, (List<String>) spinnerModel.items);
                spinnerViewHolder.list_item.setAdapter(adapter);
                spinnerViewHolder.txtLayout.setHint(spinnerModel.title);

                break;
            case TextField:
                TextFieldViewHolder textFieldViewHolder = (TextFieldViewHolder) holder;
                TextFieldModel textFieldModel = (TextFieldModel) items.get(position);

                textFieldViewHolder.textField.setHint(textFieldModel.title);
                textFieldViewHolder.edt.setText((String)textFieldModel.value);
                textFieldViewHolder.edt.setInputType(textFieldModel.inputType);

                textFieldViewHolder.textField.setErrorEnabled(false);

                if(textFieldModel.isError) {
                    textFieldViewHolder.textField.setError(textFieldModel.errorSTr);
                    textFieldViewHolder.textField.setErrorEnabled(true);
                } else {
                    textFieldViewHolder.textField.setErrorEnabled(false);
                }

                break;

            default:
                break;
        }

        BaseViewHolder baseViewHolder = (BaseViewHolder) holder;
        baseViewHolder.onValueChanged = new OnValueChanged() {
            @Override
            public void OnChanged(Object value) {
                items.get(position).value = value;      //TODO: check valid value here
            }
        };

        baseViewHolder.onAdapterChangeListener = new OnFormItemChangeListener() {
            @Override
            public void onChange() {
                if(onClientListener != null) {
                    onClientListener.onItemAdapterChanged(position);
                }
            }
        };

        if (!items.get(position).isEnable) {
            baseViewHolder.bg.setBackgroundColor(activity.getResources().getColor(R.color.disable_bg_color));
            baseViewHolder.bg.setClickable(true);
            baseViewHolder.bg.setOnClickListener(v -> {
                //do nothing
            });
        }
    }

    public void setItemChangeListener(OnItemAdapterChangeListener onAdapterChangeListener) {
        this.onClientListener = onAdapterChangeListener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public abstract class BaseViewHolder extends RecyclerView.ViewHolder{
        public View bg;
        public int position;
        public OnValueChanged onValueChanged;
        public OnFormItemChangeListener onAdapterChangeListener;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            bg = itemView.findViewById(R.id.bg);
        }
    }

    public class TextFieldViewHolder extends BaseViewHolder {
        public TextInputLayout textField;
        public TextInputEditText edt;

        public TextFieldViewHolder(@NonNull View itemView) {
            super(itemView);

            textField = itemView.findViewById(R.id.textField);
            edt = itemView.findViewById(R.id.edt);

            edt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String value = edt.getText().toString();
                    if (onValueChanged != null) {
                        onValueChanged.OnChanged(value);
                    }

                    if(onAdapterChangeListener != null) {
                        onAdapterChangeListener.onChange();
                    }
                }
            });
        }
    }

    public class DatetimeViewHolder extends BaseViewHolder {
        public TextInputLayout txtLayout;
        public TextInputEditText txtValue;
        public DatePickerType datePickerType;

        public DatetimeViewHolder(@NonNull View itemView, AppCompatActivity activity) {
            super(itemView);

            txtLayout = itemView.findViewById(R.id.txtLayout);
            txtValue = itemView.findViewById(R.id.txtValue);

            LinearLayout ll_datetime = itemView.findViewById(R.id.ll_datetime);

            ll_datetime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDateDialog(activity, datePickerType);
                }
            });
        }

        private void showDateDialog(AppCompatActivity activity, DatePickerType datePickerType) {
            switch (datePickerType) {
                case NORMAL:
                    showDatePicker();
                    break;

                case MONTH_YEAR_ONLY:
                    showMonthYearPicker(activity);
                    break;
            }
        }

        private void showMonthYearPicker(AppCompatActivity activity) {
            MonthYearPickerDialog pd = new MonthYearPickerDialog();

            pd.setListener((view, year, month, dayOfMonth) -> {
                Date date = Utils.createDate(year, month, 1);

                if (date != null) {
                    invokeValue(date);
                    txtValue.setText(Utils.dateToString(date, Constant.MONTH_YEAR_FORMAT));
                }
            });

            pd.show(activity.getSupportFragmentManager(), "MonthYearPickerDialog");
        }

        private void showDatePicker() {
            MaterialDatePicker datePicker = MaterialDatePicker
                    .Builder
                    .datePicker()
                    .setTitleText(activity.getString(R.string.select_date))
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();
            datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                @Override
                public void onPositiveButtonClick(Object selection) {
                    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(Constant.UTC));
                    calendar.setTimeInMillis((Long) selection);

                    Date value = calendar.getTime();
                    invokeValue(value);
                    txtValue.setText(datePicker.getHeaderText());
                }
            });

            datePicker.show(activity.getSupportFragmentManager(), "tag");
        }

        private void invokeValue(Date date) {
            if (onValueChanged != null) {
                onValueChanged.OnChanged(date);
            }

            if(onAdapterChangeListener != null) {
                onAdapterChangeListener.onChange();
            }
        }
    }

    public class SpinnerViewHolder extends BaseViewHolder{

        public AutoCompleteTextView list_item;
        public TextInputLayout txtLayout;

        public SpinnerViewHolder(@NonNull View itemView) {
            super(itemView);

            list_item = itemView.findViewById(R.id.list_item);
            txtLayout = itemView.findViewById(R.id.txtLayout);

            list_item.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String value = list_item.getText().toString();
                    if (onValueChanged != null) {
                        onValueChanged.OnChanged(value);
                    }

                    if(onAdapterChangeListener != null) {
                        onAdapterChangeListener.onChange();
                    }
                }
            });
        }
    }

}