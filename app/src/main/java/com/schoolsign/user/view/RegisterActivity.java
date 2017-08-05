package com.schoolsign.user.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.schoolsign.MainActivity;
import com.schoolsign.R;
import com.schoolsign.app.ClientCons;
import com.schoolsign.app.Manager;
import com.schoolsign.app.NetCons;
import com.schoolsign.app.RetrofitClient;
import com.schoolsign.base.BaseActivity;
import com.schoolsign.common.retrofit.HttpResult;
import com.schoolsign.common.rx2.RxSchedulers;
import com.schoolsign.user.bean.SchoolResult;
import com.schoolsign.user.bean.User;
import com.schoolsign.utils.Utils;
import com.schoolsign.views.IconFontTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RegisterActivity extends BaseActivity {


    @BindView(R.id.et_nickName)
    EditText mEtNickName;
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.user_te)
    RadioButton mUserTe;
    @BindView(R.id.user_st)
    RadioButton mUserSt;
    @BindView(R.id.userType)
    RadioGroup mUserType;
    @BindView(R.id.register)
    TextView mRegister;
    @BindView(R.id.to_login)
    TextView mTologin;
    @BindView(R.id.clear)
    IconFontTextView mClear;
    @BindView(R.id.code)
    IconFontTextView mCode;
    @BindView(R.id.show)
    IconFontTextView mShow;
    @BindView(R.id.tv_school)
    TextView mTvSchool;

    private User mUser;

    private String nickName;
    private String telephone;
    private String schoolId;
    private String password;
    private int userType;

    private SchoolResult mSchoolResult;

    @Override
    protected void onResume() {
        super.onResume();
        mSchoolResult = (SchoolResult) getIntent().getExtras().getSerializable("school");
        if (mSchoolResult != null) {
            mTvSchool.setText(mSchoolResult.getSchoolName());
        }
    }

    @Override
    protected void initView(View contextView) {
        mUser = new User();
        mUserType.clearCheck();
        mUserType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.user_te) {
                    userType = NetCons.TEACHER;
                } else if (i == R.id.user_st) {
                    userType = NetCons.STUDENT;
                }
            }
        });

        RxTextView.textChanges(mUsername)
                .debounce(300, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.<CharSequence>ioMain())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        if (charSequence.toString().length() > 0) {
                            mClear.setVisibility(View.VISIBLE);
                        } else {
                            mClear.setVisibility(View.INVISIBLE);
                        }
                    }
                });

        mPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mShow.setVisibility(b ? View.VISIBLE : View.INVISIBLE);
            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.register, R.id.to_login, R.id.clear, R.id.show, R.id.ll_school})
    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.register:
                toRegister();
                break;
            case R.id.to_login:
                startActivity(LoginActivity.class);
                finish();
                break;
            case R.id.clear:
                mUsername.setText("");
                break;
            case R.id.show:
                if (mPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    //否则隐藏密码
                    mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    //显示密码
                    mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                mPassword.setSelection(mPassword.getText().length());
                break;
            case R.id.ll_school:
                checkInfo(false);
                startActivity(SchoolActivity.class);
                break;
        }
    }

    private void toRegister() {
        if (!checkInfo(true)) {
            return;
        }

        //注册
        mUser.setUsername(telephone);
        mUser.setRegisterType(NetCons.REGISTER_PHONE);
        mUser.setSchool(mSchoolResult.getSchoolCode());

        RetrofitClient.getService()
                .register(Utils.getRequestBody(mUser, User.class))
                .compose(RxSchedulers.<HttpResult<User>>ioMain())
                .subscribe(new Observer<HttpResult<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mRxManager.add(d);
                    }

                    @Override
                    public void onNext(HttpResult<User> httpResult) {
                        String s = httpResult.toString();
                        Log.i(TAG, "s:" + s);
                        switch (httpResult.getCode()) {
                            case NetCons.PHONE_EXIST:
                                Snackbar.make(mRegister, "该手机号已被注册", Snackbar.LENGTH_SHORT).show();
                                break;
                            case NetCons.SYSTEM_ERROR:
                                Snackbar.make(mRegister, "网络出了点问题", Snackbar.LENGTH_SHORT).show();
                                break;
                            case NetCons.SECCESS:
                                User user = httpResult.getResult();
                                Manager.setUser(user);
                                SharedPreferences.Editor editor = getSharedPreferences(ClientCons.USER_DATA, MODE_PRIVATE).edit();
                                editor.putString("userName", user.getUsername());
                                editor.putString("password", user.getPwd());
                                editor.commit();

                                startActivity(MainActivity.class);
                                finish();
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete");
                    }
                });
    }

    private boolean checkInfo(boolean isToast) {
        nickName = mEtNickName.getText().toString().trim();
        mUser.setNickName(nickName);
        if (isToast&&(nickName.length() == 0 || nickName.length() > 15)) {
            Snackbar.make(mEtNickName, "请正确填写昵称，1-15个字符", Snackbar.LENGTH_SHORT).show();
            return false;
        }

        telephone = mUsername.getText().toString().trim();
        mUser.setTelephone(telephone);
        if (isToast&&telephone.length() != 11) {
            Snackbar.make(mEtNickName, "请正确填写手机号，11位数字", Snackbar.LENGTH_SHORT).show();
            return false;
        }

        mUser.setUserType(userType);
        if (isToast&&userType == 0) {
            Snackbar.make(mEtNickName, "请选择一个身份注册，老师或学生", Snackbar.LENGTH_SHORT).show();
            return false;
        }
        password = mPassword.getText().toString().trim();
        mUser.setPwd(password);
        if (isToast&&(password.length() < 6 || password.length() > 15)) {
            Snackbar.make(mEtNickName, "请正确填写密码，6-15位", Snackbar.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
