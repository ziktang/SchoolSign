package com.schoolsign.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

import com.schoolsign.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by tctctc on 2016/11/7.
 */

public class TimeTextView extends AppCompatTextView {

    private int startNum = 60;
    private int endNum = 0;
    private int duration = 60*1000;
    private String normalStr = "";
    private String endStr = "";
    private DateFormat mDateFormat;
    private ObjectAnimator objectAnimator;

    public TimeTextView(Context context) {
        this(context,null);
    }

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        normalStr = (String) getText();
        mDateFormat = new SimpleDateFormat("mm分ss秒");
        setCheckCode();
    }

    private void setTime(int time){
        String t = mDateFormat.format(time);
        this.setText(t);
    }

    private void setCheckCode(){
        objectAnimator = ObjectAnimator.ofInt(this,"time",startNum,endNum).setDuration(startNum-endNum);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                TimeTextView.this.setClickable(false);
                TimeTextView.this.setBackground(getResources().getDrawable(R.drawable.code_bg_click));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                TimeTextView.this.setClickable(true);
                TimeTextView.this.setBackground(getResources().getDrawable(R.drawable.code_bg));
                TimeTextView.this.setText(normalStr);
            }
        });
    }

    public void start(){
        objectAnimator.start();
    }


    public void setRange(int startNum,int endNum){
        this.startNum = startNum;
        this.endNum = endNum;
    }

    public void setNormalStr(String normalStr) {
        this.normalStr = normalStr;
    }


    public void setEndStr(String endStr) {
        this.endStr = endStr;
    }
}
