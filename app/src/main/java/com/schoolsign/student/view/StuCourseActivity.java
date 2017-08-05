package com.schoolsign.student.view;

import android.net.wifi.ScanResult;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.schoolsign.R;
import com.schoolsign.adapter.StuLessonAdapter;
import com.schoolsign.app.Manager;
import com.schoolsign.app.NetCons;
import com.schoolsign.app.RetrofitClient;
import com.schoolsign.base.BaseToolBarActivity;
import com.schoolsign.common.retrofit.HttpResult;
import com.schoolsign.common.rx2.RxSchedulers;
import com.schoolsign.user.bean.StuLessonResult;
import com.schoolsign.utils.Utils;
import com.schoolsign.utils.WifiAPUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class StuCourseActivity extends BaseToolBarActivity implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.stu_lesson_list)
    EasyRecyclerView mStuLessonList;
    @BindView(R.id.refresh_layouot)
    SwipeRefreshLayout mRefreshLayouot;
    @BindView(R.id.lessonName)
    TextView mLessonName;
    @BindView(R.id.sign)
    TextView mSign;
    @BindView(R.id.sign_time)
    TextView mSignTime;
    @BindView(R.id.cl_sign)
    ConstraintLayout mClSign;

    private StuLessonAdapter mAdapter;

    private StuLessonResult mSignLesson;


    @Override
    protected void initView(View contextView) {

        setCenterTitle(Manager.getCourse().getCname());

        mAdapter = new StuLessonAdapter(this);
        mStuLessonList.setAdapter(mAdapter);
        mStuLessonList.setLayoutManager(new LinearLayoutManager(this));

        mRefreshLayouot.setOnRefreshListener(this);
        mRefreshLayouot.setColorSchemeResources(android.R.color.holo_blue_bright, android
                .R.color.holo_orange_light, android.R.color.holo_green_light, android.R.color
                .holo_red_dark);

        RxView.clicks(mSign).debounce(2, TimeUnit.SECONDS)
                .filter(new Predicate<Object>() {
                    @Override
                    public boolean test(Object o) throws Exception {
                        if (sign()) {
                            return true;
                        } else {
                            Snackbar.make(mStuLessonList, "请在老师附近签到", Snackbar.LENGTH_SHORT).show();
                            return false;
                        }
                    }
                })
                .observeOn(Schedulers.io())
                .switchMap(new Function<Object, ObservableSource<HttpResult<StuLessonResult>>>() {

                    @Override
                    public ObservableSource<HttpResult<StuLessonResult>> apply(Object o) throws Exception {
                        return RetrofitClient.getService().sign(mSignLesson.getLid(), Manager.getUid());
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<StuLessonResult>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        mRxManager.add(d);
                    }

                    @Override
                    public void onNext(HttpResult<StuLessonResult> httpResult) {
                        Log.i(TAG, "onNext");
                        Log.i(TAG, "httpResult:" + httpResult.toString());
                        switch (httpResult.getCode()) {
                            case NetCons.SECCESS:
                            case NetCons.LESSON_FINISHED:
                                refreshData();
                                break;
                            case NetCons.SYSTEM_ERROR:
                                Snackbar.make(mStuLessonList, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError");
                        Snackbar.make(mStuLessonList, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete");
                    }
                });

        refreshData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_stu_course;
    }

    @Override
    public void onViewClick(View view) {
    }

    private boolean sign() {
        List<ScanResult> scanResults = WifiAPUtil.getInstance(this).startScan(this);
        for (ScanResult result : scanResults) {
            String bssid = result.BSSID;
            bssid = bssid.substring(bssid.length()-14);
            if (bssid.equalsIgnoreCase(mSignLesson.getSignCode())) {
                return true;
            }
        }
        return false;
    }

    public void refreshData() {
        RetrofitClient.getService().stuAllLesson(Manager.getCid(), Manager.getUid())
                .compose(RxSchedulers.<HttpResult<List<StuLessonResult>>>ioMain())
                .subscribe(new Observer<HttpResult<List<StuLessonResult>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mRxManager.add(d);
                    }

                    @Override
                    public void onNext(HttpResult<List<StuLessonResult>> httpResult) {
                        Log.i(TAG, "onNext");
                        Log.i(TAG, "httpResult:" + httpResult.toString());
                        switch (httpResult.getCode()) {
                            case NetCons.SYSTEM_ERROR:
                                Snackbar.make(mStuLessonList, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                                break;
                            case NetCons.SECCESS:
                                mAdapter.clear();
                                mAdapter.addAll(httpResult.getResult());
                                mAdapter.notifyDataSetChanged();

                                mSignLesson = httpResult.getResult().get(0);
                                if (httpResult.getResult().size() > 0 && mSignLesson.getLessonState() == NetCons.SIGNNING) {
                                    mClSign.setVisibility(View.VISIBLE);
                                    mLessonName.setText(mSignLesson.getLname());
                                    String s = Utils.formatUTC(Long.valueOf(mSignLesson.getStartTime()), null);
                                    String e = Utils.formatUTC(Long.valueOf(mSignLesson.getEndTime()), null);
                                    mSignTime.setText("时间: " + s.substring(s.length() - 11, s.length()) + " - " + e.substring(e.length() - 11, e.length()));

                                    if (mSignLesson.getSignState() == NetCons.SIGNED) {
                                        mSign.setText("已签到");
                                        mSign.setEnabled(false);
                                    } else {
                                        mSign.setText("签到");
                                        mSign.setEnabled(true);
                                    }
                                } else {
                                    mClSign.setVisibility(View.GONE);
                                }
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
                        Log.i(TAG, "onComplete");
                        mRefreshLayouot.setRefreshing(false);
                    }
                });
    }


    @Override
    public void onRefresh() {
        refreshData();
    }
}
