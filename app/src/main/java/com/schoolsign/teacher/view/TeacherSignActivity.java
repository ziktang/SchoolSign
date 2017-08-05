package com.schoolsign.teacher.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.schoolsign.R;
import com.schoolsign.adapter.SignAdapter;
import com.schoolsign.app.NetCons;
import com.schoolsign.app.RetrofitClient;
import com.schoolsign.base.BaseToolBarActivity;
import com.schoolsign.common.retrofit.HttpResult;
import com.schoolsign.common.rx2.RxSchedulers;
import com.schoolsign.user.bean.Lesson;
import com.schoolsign.user.bean.Record;
import com.schoolsign.user.bean.SignStuResult;
import com.schoolsign.utils.Utils;
import com.schoolsign.utils.WifiAPUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TeacherSignActivity extends BaseToolBarActivity implements SwipeRefreshLayout.OnRefreshListener ,RecyclerArrayAdapter.OnItemLongClickListener{

    @BindView(R.id.refresh_layouot)
    SwipeRefreshLayout mRefreshLayouot;
    @BindView(R.id.duration)
    TextView mDuration;
    @BindView(R.id.controller)
    TextView mController;
    @BindView(R.id.sign_list)
    EasyRecyclerView mSignList;
    @BindView(R.id.signed_num)
    TextView mSignedNum;
    @BindView(R.id.total_num)
    TextView mTotalNum;

    private DateFormat mDateFormat;
    private Lesson mLesson;
    private Timer timer;
    private SignAdapter mAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2:
                    mController.setText("已结束");
                    mController.setEnabled(false);
                    mDuration.setText(Utils.getTime(mLesson));
                    break;
            }
        }
    };

    @Override
    protected void initView(View contextView) {
        mLesson = (Lesson) getIntent().getSerializableExtra("lesson");
        mSignList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SignAdapter(this);
        mAdapter.setOnItemLongClickListener(this);
        mSignList.setAdapter(mAdapter);

        mDateFormat = new SimpleDateFormat("mm分ss秒");
        setCenterTitle(mLesson.getLname());
        mRefreshLayouot.setOnRefreshListener(this);
        mRefreshLayouot.setColorSchemeResources(android.R.color.holo_blue_bright, android
                .R.color.holo_orange_light, android.R.color.holo_green_light, android.R.color
                .holo_red_dark);
    }

    private void stateRefresh() {

        if (mLesson.getState() == NetCons.SIGNNING) {
            daojishi();
            mDuration.setText(Utils.getTime(mLesson));
        } else if (mLesson.getState() == NetCons.UNPUBLISH) {
            mController.setText("开始签到");
            mDuration.setText("持续时间 :" + mLesson.getDuration() + "分钟");
        } else {
            mDuration.setText(Utils.getTime(mLesson));
            if (timer != null) {
                timer.cancel();
            }
            mController.setText("已结束");
            mController.setEnabled(false);

        }
    }

    private void daojishi() {
        final long duration = Long.valueOf(mLesson.getEndTime()) - System.currentTimeMillis();

        if (duration <= 0) {
            mDuration.setText(Utils.formatUTC(Long.valueOf(mLesson.getStartTime()), null) +
                    " - " + Utils.formatUTC(Long.valueOf(mLesson.getEndTime()), null));
            if (timer != null) {
                timer.cancel();
            }
            mController.setText("已结束");
            mController.setEnabled(false);

            return;
        }


        mController.setText("结束签到");


        Log.i(TAG, "duration:" + duration);
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(2);
            }
        }, duration);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_sign;
    }


    @OnClick({R.id.controller})
    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.controller:
                if (mLesson.getState() == NetCons.UNPUBLISH) {
                    toStart();
                } else if (mLesson.getState() == NetCons.SIGNNING) {
                    toFinish();
                }
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }

    private void toFinish() {
        RetrofitClient.getService().finishSign(mLesson.getLid())
                .compose(RxSchedulers.<HttpResult<Lesson>>ioMain())
                .subscribe(new Observer<HttpResult<Lesson>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mRxManager.add(d);
                    }

                    @Override
                    public void onNext(HttpResult<Lesson> httpResult) {
                        Log.i(TAG, "onNext");
                        Log.i(TAG, "httpResult:" + httpResult.toString());
                        switch (httpResult.getCode()) {
                            case NetCons.SYSTEM_ERROR:
                                Snackbar.make(mController, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                                break;
                            case NetCons.SECCESS:
                            case NetCons.LESSON_FINISHED:
                                mLesson = httpResult.getResult();
                                stateRefresh();
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(mController, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                        Log.i(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete");
                    }
                });
    }

    private void toStart() {
        boolean result = WifiAPUtil.getInstance(this).turnOnWifiAp("签到热点", "!@^%&s7%c^#2&8&6&^%", WifiAPUtil.WifiSecurityType.WIFICIPHER_WPA2);
        if (result) {
            String l = WifiAPUtil.getMacAddress(this);

            l = l.substring(l.length() - 14);
            RetrofitClient.getService().startSign(mLesson.getLid(), l)
                    .compose(RxSchedulers.<HttpResult<Lesson>>ioMain())
                    .subscribe(new Observer<HttpResult<Lesson>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mRxManager.add(d);
                        }

                        @Override
                        public void onNext(HttpResult<Lesson> httpResult) {
                            Log.i(TAG, "onNext");
                            Log.i(TAG, "httpResult:" + httpResult.toString());
                            switch (httpResult.getCode()) {
                                case NetCons.SYSTEM_ERROR:
                                    Snackbar.make(mController, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                                    break;
                                case NetCons.SECCESS:
                                    mLesson = httpResult.getResult();
                                    stateRefresh();
                                    break;
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Snackbar.make(mController, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                            Log.i(TAG, "onError");
                        }

                        @Override
                        public void onComplete() {
                            Log.i(TAG, "onComplete");
                        }
                    });
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
        mRxManager.dispose();
    }

    @Override
    public void onRefresh() {
        refreshData();
    }


    private void refreshData() {
        RetrofitClient.getService().signStudents(mLesson.getLid())
                .compose(RxSchedulers.<HttpResult<SignStuResult>>ioMain())
                .subscribe(new Observer<HttpResult<SignStuResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mRxManager.add(d);
                    }

                    @Override
                    public void onNext(HttpResult<SignStuResult> httpResult) {
                        Log.i(TAG, "onNext");
                        Log.i(TAG, "httpResult:" + httpResult.toString());
                        switch (httpResult.getCode()) {
                            case NetCons.SYSTEM_ERROR:
                                Snackbar.make(mController, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                                break;
                            case NetCons.SECCESS:
                                mLesson = httpResult.getResult().getLesson();
                                List<Record> records = httpResult.getResult().getRecords();
                                int num = 0;
                                for (Record re : records) {
                                    if (re.getState() == NetCons.SIGNED) {
                                        num++;
                                    }
                                }
                                mSignedNum.setText(num + "");
                                mTotalNum.setText(mLesson.getTotalNum() + "");
                                mAdapter.clear();
                                mAdapter.addAll(records);
                                mAdapter.notifyDataSetChanged();
                                stateRefresh();
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRefreshLayouot.setRefreshing(false);
                        Log.i(TAG, "onError");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        mRefreshLayouot.setRefreshing(false);
                        Log.i(TAG, "onComplete");
                    }
                });
    }


    private Observer mObserver = new Observer<HttpResult<Record>>() {
        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(HttpResult<Record> httpResult) {
            Log.i(TAG,"onNext");
            Log.i(TAG,"s:"+httpResult.toString());
            switch (httpResult.getCode()) {
                case NetCons.SECCESS:
                    Record record = httpResult.getResult();
                    if (record!=null&&selectPosition>=0){
                        Record re = mAdapter.getAllData().get(selectPosition);
                        re.setSignedTime(record.getSignedTime());
                        re.setState(record.getState());
                        mAdapter.notifyItemChanged(selectPosition);
                    }
                    Log.i(TAG,"------------");
                    break;
                case NetCons.SYSTEM_ERROR:
                    Snackbar.make(mController,"网络可能出了点问题",Snackbar.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG,"onError");
        }

        @Override
        public void onComplete() {
            Log.i(TAG,"onComplete");
        }
    };

    private int selectPosition = -1;

    @Override
    public boolean onItemLongClick(int position) {
        selectPosition = position;
        final Record record = mAdapter.getAllData().get(position);
        String s = record.getState() == NetCons.SIGNED?"取消签到":"手动签到";
        new MaterialDialog.Builder(TeacherSignActivity.this).items(s)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        if (text.equals("取消签到")){
                            RetrofitClient.getService().cancelSign(record.getLid(),record.getUid())
                                    .compose(RxSchedulers.<HttpResult<Record>>ioMain())
                                    .subscribe(mObserver);

                        }else{
                            RetrofitClient.getService().doSign(record.getLid(),record.getUid())
                                    .compose(RxSchedulers.<HttpResult<Record>>ioMain())
                                    .subscribe(mObserver);
                        }
                    }
                }).show();
        return true;
    }
}
