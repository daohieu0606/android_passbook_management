package com.example.savingsbookmanagement.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;

import com.example.savingsbookmanagement.R;

public class IconButton extends androidx.appcompat.widget.AppCompatTextView {

    private Context context;

    public IconButton(Context context) {
        super(context);
        Init(context);
    }

    public IconButton(Context context, AttributeSet attrs) {
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
}
