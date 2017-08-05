package com.schoolsign.student.view;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.schoolsign.R;
import com.schoolsign.app.NetCons;
import com.schoolsign.app.RetrofitClient;
import com.schoolsign.app.Manager;
import com.schoolsign.base.BaseToolBarActivity;
import com.schoolsign.common.retrofit.HttpResult;
import com.schoolsign.common.rx2.RxSchedulers;
import com.schoolsign.user.bean.Course;
import com.schoolsign.views.IconFontTextView;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AddCourseActivity extends BaseToolBarActivity {


    @BindView(R.id.et_course)
    EditText mEtCourse;
    @BindView(R.id.search_course)
    IconFontTextView mSearchCourse;

    @Override
    protected void initView(View contextView) {
        setCenterTitle("添加课程");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_course;
    }

    @OnClick({R.id.search_course,R.id.back})
    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.search_course:
                search();
                break;
            case R.id.back:
                finish();
                break;
        }
    }


    private void search() {
        try {
            String num = mEtCourse.getText().toString().trim();
            if(TextUtils.isEmpty(num)){
                return;
            }
            int cid = Integer.parseInt(num);
            RetrofitClient.getService().lookCourse(Manager.getUid(), cid)
                    .compose(RxSchedulers.<HttpResult<Course>>ioMain())
                    .subscribe(new Observer<HttpResult<Course>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mRxManager.add(d);
                        }

                        @Override
                        public void onNext(HttpResult<Course> httpResult) {
                            Log.i(TAG, "onNext");
                            Log.i(TAG, "result:" + httpResult.toString());
                            switch (httpResult.getCode()) {
                                case NetCons.COURSE_NOT_EXIST:
                                    Snackbar.make(mEtCourse, "课程不存在", Snackbar.LENGTH_SHORT).show();
                                    break;
                                case NetCons.SYSTEM_ERROR:
                                    Snackbar.make(mEtCourse, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                                    break;
                                case NetCons.UN_JOIN:
                                case NetCons.JOINED:
                                    Intent intent = new Intent(AddCourseActivity.this,CourseDetailActivity.class);
                                    intent.putExtra("code", httpResult.getCode());
                                    intent.putExtra("course", httpResult.getResult());
                                    startActivity(intent);
                                    finish();
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
        } catch (Exception e) {
            Snackbar.make(mEtCourse, "请输入正确的课程编号", Snackbar.LENGTH_SHORT).show();
        }

    }
}
