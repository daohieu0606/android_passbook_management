package com.example.passbook.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;

public class IconLabel extends androidx.appcompat.widget.AppCompatTextView {

    private Context context;

    public IconLabel(Context context) {
        super(context);
        Init(context);
    }

    public IconLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context);
    }

    private void Init(Context context){
        this.context = context;
        this.setClickable(true);
        setGravity(Gravity.CENTER);
        Typeface face=Typeface.createFromAsset(context.getAssets(),"fonts/iconfont.otf");
        setTypeface(face);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }
}
