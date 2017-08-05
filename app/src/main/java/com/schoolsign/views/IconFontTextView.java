package com.schoolsign.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.schoolsign.utils.FontUtils;

/**
 * Created by tctctc on 2017/6/19.
 * Function:
 */

public class IconFontTextView extends AppCompatTextView {
    public IconFontTextView(Context context) {
        this(context,null);
    }
    public IconFontTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public IconFontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(FontUtils.getIconfont());
    }
}
