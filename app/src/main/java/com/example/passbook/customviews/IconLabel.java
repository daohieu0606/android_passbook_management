package com.example.passbook.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;

import com.example.passbook.R;

public class IconLabel extends androidx.appcompat.widget.AppCompatTextView {

    private Context context;

    public IconLabel(Context context) {
        super(context);
        Init(context, null);
    }

    public IconLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context, attrs);
    }

    private void Init(Context context,  AttributeSet attrs){
        this.context = context;

        setGravity(Gravity.CENTER);

        Typeface face=Typeface.createFromAsset(context.getAssets(),"fonts/iconfont.otf");
        setTypeface(face);

        if(attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IconLabel);

            int txtColor = typedArray.getColor(R.styleable.IconLabel_android_textColor,
                    context.getResources().getColor(R.color.colorPrimary));
            this.setTextColor(txtColor);

            int backgroundAttr = typedArray.getInt(R.styleable.IconLabel_bg_color, context.getResources().getColor(R.color.transparent));
            this.setBackgroundColor(backgroundAttr);

            typedArray.recycle();

        } else {
            this.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            this.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
