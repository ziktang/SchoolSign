package com.schoolsign.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.schoolsign.common.rx2.RxManager;
import com.schoolsign.utils.InputUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    /**  日志标志  **/
    protected final String TAG = this.getClass().getSimpleName();
    /**  是否设置沉浸式状态栏  **/
    private boolean isSteepStatusBar = true;
    /**  是否允许全屏  **/
    private boolean isAllowFullScreen = true;
    /**  是否允许屏幕旋转  **/
    private boolean isAllowScreenRoate = false;
    /**  Activity的试图  **/
    private View mContextView;

    public RxManager mRxManager;

    private Unbinder mUnbinder;

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParams();
        setParams();
        mContextView = LayoutInflater.from(this)
                .inflate(getLayoutId(), null);
        setContentView(mContextView);
        mRxManager = new RxManager();
        mUnbinder = ButterKnife.bind(this);
        mContext = this;
        initView(mContextView);
    }

    /**
     * 初始化Activity中的View
     * @param contextView
     */
    protected abstract void initView(View contextView);

    /**
     * 返回子Activity的布局
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 供子Activity手动设置各种参数
     */
    protected void initParams(){

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        InputUtil.isHiddenInput(ev,this);
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 点击事件
     * @param view
     */
    public abstract void onViewClick(View view);


    /**
     * 设置沉浸式状态栏
     */
    private void setSteepStatusBar(){

    }

    /**
     * Activity设置
     */
    private void setParams(){
        //设置沉浸式状态栏
        if (isSteepStatusBar){
            setSteepStatusBar();
        }
        //设置全屏
        if (isAllowFullScreen){
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        //禁止旋转屏幕
        if (!isAllowScreenRoate){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void onClick(View v) {
        onViewClick(v);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"*****onRestart()*****");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"*****"+Thread.currentThread().getStackTrace()[2].getMethodName()+"()*****");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"*****"+Thread.currentThread().getStackTrace()[2].getMethodName()+"()*****");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRxManager.dispose();
        Log.d(TAG,"*****"+Thread.currentThread().getStackTrace()[2].getMethodName()+"()*****");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"*****"+Thread.currentThread().getStackTrace()[2].getMethodName()+"()*****");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"*****"+Thread.currentThread().getStackTrace()[2].getMethodName()+"()*****");
        mUnbinder.unbind();
        mRxManager.clear();
    }

    public void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    /**
     * Activity跳转
     * @param aClass
     */
    public void startActivity(Class<?> aClass) {
        super.startActivity(new Intent(BaseActivity.this,aClass));
    }

    /**
     * 带参数的Activity跳转
     */
    public void startActivity(Class<?> aClass,Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(BaseActivity.this,aClass);
        if (bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
