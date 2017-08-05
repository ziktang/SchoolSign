package com.schoolsign;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.schoolsign.adapter.StuCourseAdapter;
import com.schoolsign.adapter.TeaCourseAdapter;
import com.schoolsign.app.NetCons;
import com.schoolsign.app.RetrofitClient;
import com.schoolsign.app.Manager;
import com.schoolsign.base.BaseToolBarActivity;
import com.schoolsign.common.retrofit.HttpResult;
import com.schoolsign.common.rx2.RxSchedulers;
import com.schoolsign.student.view.AddCourseActivity;
import com.schoolsign.student.view.StuCourseActivity;
import com.schoolsign.teacher.view.AddTeaCourseActivity;
import com.schoolsign.teacher.view.TeaCouseActivity;
import com.schoolsign.user.bean.Course;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MainActivity extends BaseToolBarActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.course_list)
    EasyRecyclerView mCourseList;
    @BindView(R.id.refresh_layouot)
    SwipeRefreshLayout mRefreshLayouot;
    private RecyclerArrayAdapter mAdapter;

    private Disposable mDisposable;

    private Observer<HttpResult<List<Course>>> mObserver = new Observer<HttpResult<List<Course>>>() {
        @Override
        public void onSubscribe(Disposable d) {
            mDisposable = d;
        }

        @Override
        public void onNext(HttpResult<List<Course>> httpResult) {
            Log.i(TAG, "onNext");
            Log.i(TAG, "body:" + httpResult.toString());
            switch (httpResult.getCode()) {
                case NetCons.SECCESS:
                    mAdapter.clear();
                    mAdapter.addAll(httpResult.getResult());
                    mAdapter.notifyDataSetChanged();
                    break;
                case NetCons.SYSTEM_ERROR:
                    Snackbar.make(mCourseList, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
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
    };

    @Override
    protected void initView(View contextView) {
        setCenterTitle("我的课程");
        if (Manager.isTeacher()) {
            mAdapter = new TeaCourseAdapter(this);
        } else {
            mAdapter = new StuCourseAdapter(this);
        }

        mCourseList.setLayoutManager(new LinearLayoutManager(this));
        mCourseList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "去课程详情页面", Toast.LENGTH_SHORT).show();
                Manager.setCourse(((List<Course>) mAdapter.getAllData()).get(position));
                if (Manager.isTeacher()) {
                    startActivity(TeaCouseActivity.class);
                }else{
                    startActivity(StuCourseActivity.class);
                }
            }
        });

        mRefreshLayouot.setOnRefreshListener(this);
        mRefreshLayouot.setColorSchemeResources(android.R.color.holo_blue_bright, android
                .R.color.holo_orange_light, android.R.color.holo_green_light, android.R.color
                .holo_red_dark);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }

    private void refreshData() {
        Observable<HttpResult<List<Course>>> observable = null;

        Log.i(TAG, "userType:" + Manager.getUser().getUserType());
        if (Manager.isTeacher()) {
            observable = RetrofitClient.getService().allTeacherCourse(Manager.getUid());
        } else {

            observable = RetrofitClient.getService().allStudentCourse(Manager.getUid());
        }
        observable.compose(RxSchedulers.<HttpResult<List<Course>>>ioMain()).subscribe(mObserver);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initParams() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    @OnClick({R.id.add})
    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                Toast.makeText(this, "去添加课程", Toast.LENGTH_SHORT).show();
                if (Manager.isTeacher()) {
                    startActivity(AddTeaCourseActivity.class);
                } else {
                    startActivity(AddCourseActivity.class);
                }

                break;
        }
    }

    @Override
    public void onRefresh() {
        refreshData();
    }
}