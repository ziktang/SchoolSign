package com.schoolsign.student.view;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.schoolsign.R;
import com.schoolsign.app.NetCons;
import com.schoolsign.app.RetrofitClient;
import com.schoolsign.app.Manager;
import com.schoolsign.base.BaseToolBarActivity;
import com.schoolsign.common.retrofit.HttpResult;
import com.schoolsign.common.rx2.RxSchedulers;
import com.schoolsign.user.bean.Course;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CourseDetailActivity extends BaseToolBarActivity {


    @BindView(R.id.course_name)
    TextView mCourseName;
    @BindView(R.id.course_num)
    TextView mCourseNum;
    @BindView(R.id.course_tea_name)
    TextView mCourseTeaName;
    @BindView(R.id.course_remark)
    TextView mCourseRemark;
    @BindView(R.id.join)
    TextView mJoin;
    @BindView(R.id.require)
    EditText mRequire;

    private Course mCourse;

    private int mCode;

    @Override
    protected void initView(View contextView) {

        mCode = getIntent().getIntExtra("code", 0);
        mCourse = (Course) getIntent().getSerializableExtra("course");
        mCourseName.setText(mCourse.getCname());
        mCourseNum.setText("课程编号 : "+mCourse.getCid());
        mCourseTeaName.setText("上课老师 : "+mCourse.getTname());
        mCourseRemark.setText("介绍 : "+mCourse.getRemark());
        setCenterTitle(mCourse.getCname());
        if (mCode == NetCons.JOINED) {
            mJoin.setBackground(getResources().getDrawable(R.drawable.bt_oval_gray));
            mJoin.setText("已加入");
            mJoin.setClickable(false);
        } else {
            mJoin.setBackground(getResources().getDrawable(R.drawable.bt_oval));
            mJoin.setText("申请加入");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_detail;
    }



    @OnClick(R.id.join)
    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.join:
                String require = mRequire.getText().toString().trim();
                if (TextUtils.isEmpty(require)) {
                    Snackbar.make(mCourseName, "请填写验证信息", Snackbar.LENGTH_SHORT).show();
                }
                RetrofitClient.getService().joinCourse(Manager.getUid(), mCourse.getCid(), require)
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
                                        Snackbar.make(mCourseName, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                                        break;
                                    case NetCons.SECCESS:
                                        Snackbar.make(mCourseName, "已申请", Snackbar.LENGTH_SHORT).show();
                                        mRequire.setText("");
                                        break;
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i(TAG, "onError");
                                e.printStackTrace();
                                Snackbar.make(mCourseName, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                                Log.i(TAG, "onComplete");
                            }
                        });
                break;
        }
    }
}
