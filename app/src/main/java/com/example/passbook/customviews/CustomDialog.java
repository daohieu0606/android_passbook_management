package com.example.passbook.customviews;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.passbook.R;
import com.example.passbook.intefaces.OnDialogButtonClick;

public class CustomDialog extends Dialog implements View.OnClickListener{
    protected AppCompatActivity activity;
    protected RelativeLayout body;
    protected Button btnYes;
    protected Button btnNo;
    protected TextView txtTitle;

    public OnDialogButtonClick onDialogButtonClick;

    public CustomDialog(@NonNull AppCompatActivity activity,@NonNull Integer bodyView, String title) {
        super(activity);

        this.activity = activity;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_two_button);
        btnYes = (Button) findViewById(R.id.btnYes);
        btnNo = (Button) findViewById(R.id.btnNo);
        txtTitle = findViewById(R.id.txtTitle);

        txtTitle.setText(title);
        body = findViewById(R.id.body);

        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        layoutInflater.inflate(bodyView, body, true);

        btnYes.setOnClickListener(this::onClick);
        btnNo.setOnClickListener(this::onClick);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnYes:
                handleYesButton();
                break;

            case R.id.btnNo:
                handleNoButton();
                break;
        }
    }

    protected void handleNoButton() {
        if (onDialogButtonClick != null) {
            onDialogButtonClick.onNegativeClick();
        }
        this.dismiss();
    }

    protected void handleYesButton() {
        if(onDialogButtonClick != null) {
            onDialogButtonClick.onPositiveClick();
        }
    }

    public RelativeLayout getBody() {
        return body;
    }
}