package com.schoolsign.teacher.view;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.schoolsign.R;
import com.schoolsign.adapter.StudentAdapter;
import com.schoolsign.app.NetCons;
import com.schoolsign.app.RetrofitClient;
import com.schoolsign.app.Manager;
import com.schoolsign.base.BaseToolBarActivity;
import com.schoolsign.common.retrofit.HttpResult;
import com.schoolsign.common.rx2.RxSchedulers;
import com.schoolsign.user.bean.Course;
import com.schoolsign.user.bean.StudentResult;
import com.schoolsign.utils.UiUtils;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
public class AllStudentActivity extends BaseToolBarActivity implements StudentAdapter.RequireListener,SwipeRefreshLayout.OnRefreshListener  {


    @BindView(R.id.info)
    TextView mInfo;
    @BindView(R.id.student_list)
    EasyRecyclerView mStudentList;
    @BindView(R.id.refresh_layouot)
    SwipeRefreshLayout mRefreshLayouot;

    private StudentAdapter mAdapter;

    private Course mCourse;

    @Override
    protected void initView(View contextView) {
        setCenterTitle("学生列表");
        mCourse = Manager.getCourse();
        mAdapter = new StudentAdapter(this);
        mAdapter.setListener(this);
        mStudentList.setLayoutManager(new LinearLayoutManager(this));
        mStudentList.setAdapter(mAdapter);
        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, (int)UiUtils.dpToPx(this,0.5f), (int)UiUtils.dpToPx(this,0.5f),0);//颜色 & 高度 & 左边距 & 右边距
        mStudentList.addItemDecoration(itemDecoration);
        mRefreshLayouot.setOnRefreshListener(this);
        mRefreshLayouot.setColorSchemeResources(android.R.color.holo_blue_bright, android
                .R.color.holo_orange_light, android.R.color.holo_green_light, android.R.color
                .holo_red_dark);
        refreshData();
    }

    private void refreshData() {
        RetrofitClient.getService().allStudent(Manager.getUid(), Manager.getCid())
                .compose(RxSchedulers.<HttpResult<List<StudentResult>>>ioMain())
                .subscribe(new Observer<HttpResult<List<StudentResult>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mRxManager.add(d);
                    }

                    @Override
                    public void onNext(HttpResult<List<StudentResult>> httpResult) {
                        Log.i(TAG, "refreshData onNext");
                        Log.i(TAG, "result:" + httpResult.toString());
                        switch (httpResult.getCode()) {
                            case NetCons.SYSTEM_ERROR:
                                Snackbar.make(mStudentList, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                                break;
                            case NetCons.SECCESS:
                                mAdapter.clear();
                                mAdapter.addAll(httpResult.getResult());
                                mAdapter.notifyDataSetChanged();

                                int num = 0;
                                for(int i = 0;i<httpResult.getResult().size();i++){
                                    StudentResult result = httpResult.getResult().get(i);
                                    if(result.getJoinState() == NetCons.JOIN){
                                        ++num;
                                    }
                                }
                                mInfo.setText(num+"名学生  "+(httpResult.getResult().size()-num)+"个申请");
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_all_student;
    }


    @Override
    public void onViewClick(View view) {

    }

    @Override
    public void dealRequire(int position, boolean agree) {
        if (mAdapter.getItem(position).getJoinState() == NetCons.APPLYING) {
            StudentResult result = mAdapter.getItem(position);
            int a = agree ? NetCons.AGREE : NetCons.DISAGREE;
            RetrofitClient.getService().dealJoin(result.getUid(), Manager.getCid(), a)
                    .compose(RxSchedulers.<HttpResult>ioMain())
                    .subscribe(new Observer<HttpResult>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                           mRxManager.add(d);
                        }

                        @Override
                        public void onNext(HttpResult httpResult) {
                            Log.i(TAG, "onNext");
                            Log.i(TAG, "result:" + httpResult.toString());
                            switch (httpResult.getCode()) {
                                case NetCons.SYSTEM_ERROR:
                                    Snackbar.make(mStudentList, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                                    break;
                                case NetCons.SECCESS:
                                    refreshData();
                                    break;
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i(TAG, "onError");
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {
                            Log.i(TAG, "onComplete");
                        }
                    });
        }
    }

    @Override
    public void onRefresh() {
        refreshData();
    }
}
