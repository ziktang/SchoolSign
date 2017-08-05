package com.schoolsign.base;

import android.content.Context;

import com.schoolsign.common.rx2.RxManager;

/**
 * Created by tctctc on 2016/11/17.
 */

public abstract class BasePresenter<M, V> {
    public M mModel;
    public V mView;
    public Context mContext;
    public RxManager mRxManager = new RxManager();

    public void setVM(M m, V v) {
        this.mModel = m;
        this.mView = v;
        this.onStart();
    }

    abstract public void onStart();

    public void onDestroy() {
        mRxManager.clear();
    }
}
