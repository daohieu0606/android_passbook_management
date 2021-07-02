package com.example.passbook.customviews;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.passbook.R;
import com.google.android.material.button.MaterialButton;

import org.apache.commons.lang3.StringUtils;

public class CustomButton extends MaterialButton {
    public CustomButton(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_bg_normal) );
        } else {
            this.setBackground(ContextCompat.getDrawable(context, R.drawable.button_bg_normal));
        }

        this.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        this.setTextColor(Color.WHITE);
        this.setTextSize(23.0f);
        this.setGravity(Gravity.CENTER_VERTICAL);
    }
}
