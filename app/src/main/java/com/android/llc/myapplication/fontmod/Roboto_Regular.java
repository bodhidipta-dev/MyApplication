package com.android.llc.myapplication.fontmod;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


public class Roboto_Regular extends AppCompatTextView {
    public Roboto_Regular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        init();
    }

    public Roboto_Regular(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init();
    }

    public Roboto_Regular(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    public void init() {

        super.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
                "Roboto-Regular.ttf"));

    }
}
