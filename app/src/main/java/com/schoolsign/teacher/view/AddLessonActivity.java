package com.schoolsign.teacher.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolsign.R;
import com.schoolsign.app.Manager;
import com.schoolsign.app.NetCons;
import com.schoolsign.app.RetrofitClient;
import com.schoolsign.base.BaseToolBarActivity;
import com.schoolsign.common.retrofit.HttpResult;
import com.schoolsign.common.rx2.RxSchedulers;
import com.schoolsign.user.bean.Lesson;
import com.schoolsign.utils.Utils;
import com.schoolsign.utils.WifiAPUtil;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class AddLessonActivity extends BaseToolBarActivity {

    @BindView(R.id.et_lessonName)
    EditText mEtLessonName;
    @BindView(R.id.isPublish)
    Switch mIsPublish;
    @BindView(R.id.createLesson)
    TextView mCreateLesson;
    @BindView(R.id.duration)
    EditText mDuration;

    private String lname;
    private int duration;
    private Date mStartDate;
    private Date mEndDate;

    DateFormat mFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

    @Override
    protected void initView(View contextView) {
        setCenterTitle("创建课次");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_lesson;
    }

    @OnClick({R.id.createLesson})
    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.createLesson:
                create();
                break;
        }

    }

    private void create() {
        if (!checkInfo()) {
            return;
        }
        Lesson lesson = new Lesson();
        lesson.setCid(Manager.getCid());
        lesson.setLname(lname);
        lesson.setDuration(duration);
        if (mIsPublish.isChecked()) {
            boolean result = WifiAPUtil.getInstance(this).turnOnWifiAp("签到热点", "!@^%&s7%c^#2&8&6&^%", WifiAPUtil.WifiSecurityType.WIFICIPHER_WPA2);
            if (result) {
                String l = WifiAPUtil.getMacAddress(this);
                l = l.substring(l.length() - 14);
                lesson.setSignCode(l);
                lesson.setState(NetCons.SIGNNING);
            }else{
                showToast("未能开启热点，不能立即发布");
                lesson.setState(NetCons.UNPUBLISH);
            }
        } else {
            lesson.setState(NetCons.UNPUBLISH);
        }

        RequestBody body = Utils.getRequestBody(lesson, Lesson.class);
        RetrofitClient.getService().createLesson(body)
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
                            case NetCons.EXIST_SIGN_LESSON:
                                showToast("本门课程已经有正在签到");
                                break;
                            case NetCons.SECCESS:
                                AddLessonActivity.this.finish();
                                break;
                            case NetCons.SYSTEM_ERROR:
                                Snackbar.make(mCreateLesson, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError");
                        Snackbar.make(mCreateLesson, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete");
                    }
                });
    }

    private boolean checkInfo() {


        lname = mEtLessonName.getText().toString().trim();
        if (lname.length() < 1 || lname.length() > 15) {
            Snackbar.make(mCreateLesson, "课次名字，1-15个字符", Snackbar.LENGTH_SHORT).show();
            return false;
        }

        try {
            String t = mDuration.getText().toString().trim();
            if (t.length() == 0) {
                Snackbar.make(mCreateLesson, "请输入签到时间，大于0", Snackbar.LENGTH_SHORT);
                return false;
            }
            duration = Integer.valueOf(mDuration.getText().toString().trim());
        } catch (Exception e) {
            Snackbar.make(mCreateLesson, "请输入签到时间，大于0", Snackbar.LENGTH_SHORT);
            return false;
        }
        if (duration == 0) {
            Snackbar.make(mCreateLesson, "请输入签到时间，大于0", Snackbar.LENGTH_SHORT);
            return false;
        }
        return true;
    }
}
