package com.schoolsign.teacher.view;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.schoolsign.MainActivity;
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

public class AddTeaCourseActivity extends BaseToolBarActivity {

    @BindView(R.id.et_courseName)
    EditText mEtCourseName;
    @BindView(R.id.et_courseDescribe)
    EditText mEtCourseDescribe;

    private String courseName;
    private String courseDescribe;


    private Observer<HttpResult<Course>> mObserver = new Observer<HttpResult<Course>>() {
        @Override
        public void onSubscribe(Disposable d) {
            mRxManager.add(d);
        }

        @Override
        public void onNext(HttpResult<Course> httpResult) {
            Log.i(TAG, "onNext");
            Log.i(TAG, "body:" + httpResult.toString());
            switch (httpResult.getCode()) {
                case NetCons.SECCESS:
                    startActivity(MainActivity.class);
                    finish();
                    break;
                case NetCons.SYSTEM_ERROR:
                    Snackbar.make(mEtCourseName,"网络好像出了点问题",Snackbar.LENGTH_SHORT).show();
                    break;
                case NetCons.MY_COURSE_EXIST:
                    Snackbar.make(mEtCourseName,"您已创建过同名课程",Snackbar.LENGTH_SHORT).show();
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
    };

    @Override
    protected void initView(View contextView) {
        setCenterTitle("创建课程");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_tea_course;
    }



    @OnClick({R.id.create_course})
    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.create_course:
                createCourse();
                break;
        }
    }

    private void createCourse() {
        if (!checkInfo()) {
            return;
        }
        RetrofitClient.getService().createCourse(Manager.getUser().getUid(),courseName,courseDescribe)
                .compose(RxSchedulers.<HttpResult<Course>>ioMain())
                .subscribe(mObserver);
    }

    private boolean checkInfo() {
        courseName = mEtCourseName.getText().toString().trim();
        if (courseName.length() == 0 || courseName.length() > 20) {
            Snackbar.make(mEtCourseName, "请正确填写课程名称1-20字符", Snackbar.LENGTH_SHORT).show();
            return false;
        }
        courseDescribe = mEtCourseDescribe.getText().toString().trim();
        if (courseDescribe.length() == 0 || courseName.length() > 50) {
            Snackbar.make(mEtCourseName, "请正确填写课程描述1-50字符", Snackbar.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
