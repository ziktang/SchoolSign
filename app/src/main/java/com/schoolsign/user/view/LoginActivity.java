package com.schoolsign.user.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.schoolsign.MainActivity;
import com.schoolsign.R;
import com.schoolsign.app.ClientCons;
import com.schoolsign.app.NetCons;
import com.schoolsign.app.RetrofitClient;
import com.schoolsign.app.Manager;
import com.schoolsign.base.BaseActivity;
import com.schoolsign.common.retrofit.HttpResult;
import com.schoolsign.common.rx2.RxSchedulers;
import com.schoolsign.user.bean.User;
import com.schoolsign.utils.InputUtil;
import com.schoolsign.utils.NetUtils;
import com.schoolsign.utils.StatusBarUtils;
import com.schoolsign.utils.Utils;
import com.schoolsign.views.IconFontTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login)
    TextView mLogin;
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.clear)
    IconFontTextView mClear;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.show)
    IconFontTextView mShow;
    private User mUser;


    private Observer mObserver = new Observer<HttpResult<User>>() {
        @Override
        public void onSubscribe(Disposable d) {
            mRxManager.add(d);
        }

        @Override
        public void onNext(HttpResult<User> httpResult) {
            String s = httpResult.toString();
            Log.i(TAG, "s:" + s);
            switch (httpResult.getCode()) {
                case NetCons.DATA_ERROR:
                    Snackbar.make(mPassword, "用户名或密码错误", Snackbar.LENGTH_SHORT).show();
                    break;
                case NetCons.SYSTEM_ERROR:
                    Snackbar.make(mPassword, "网络出了点问题2222", Snackbar.LENGTH_SHORT).show();
                    break;
                case NetCons.SECCESS:
                    User user = httpResult.getResult();
                    Manager.setUser(user);
                    SharedPreferences.Editor editor = getSharedPreferences(ClientCons.USER_DATA, MODE_PRIVATE).edit();
                    editor.putString("username", user.getUsername());
                    editor.putString("password", user.getPwd());
                    editor.commit();
                    startActivity(MainActivity.class);
                    finish();
                    break;
            }
        }

        @Override
        public void onError(Throwable e) {
            Snackbar.make(mPassword, "网络出了点问题", Snackbar.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        @Override
        public void onComplete() {
            Log.i(TAG, "onComplete");
        }
    };

    @Override
    protected void initView(View contextView) {
        StatusBarUtils.setTranslucent(this);
        initTextView();
    }

    private void initTextView() {

        mUser = new User();

//        SharedPreferences preferences = getSharedPreferences(ClientCons.USER_DATA, Context.MODE_PRIVATE);
//        String username = preferences.getString("username","");
//        String password = preferences.getString("password","");
//
//        if(!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password)){
//            mUser.setUsername(username);
//            mUser.setPwd(password);
//            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), new Gson().toJson(mUser, User.class));
//            RetrofitClient.getService().login(body).compose(RxSchedulers.<ResponseBody>ioMain()).subscribe(mObserver);
//        }

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
        return R.layout.activity_login;
    }

    @OnClick({R.id.to_register, R.id.clear, R.id.show, R.id.login})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.to_register:
                if (NetUtils.isAvailableByPing())
                    startActivity(SchoolActivity.class);
                else {
                    Snackbar.make(mLogin, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                }
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
            case R.id.login:
                toLogin();
                break;
        }
    }

    private void toLogin() {
        if (!checkInfo(true)) {
            return;
        }
        RetrofitClient.getService().login(Utils.getRequestBody(mUser, User.class)).compose(RxSchedulers.<HttpResult<User>>ioMain()).subscribe(mObserver);
    }

    private boolean checkInfo(boolean isToast) {
        String username = mUsername.getText().toString().trim();
        mUser.setUsername(username);
        if (isToast && username.length() != 11) {
            Snackbar.make(mPassword, "请正确填写手机号，11位数字", Snackbar.LENGTH_SHORT).show();
            return false;
        }

        String password = mPassword.getText().toString().trim();
        mUser.setPwd(password);
        if (isToast && (password.length() < 6 || password.length() > 15)) {
            Snackbar.make(mPassword, "请正确填写密码，6-15位", Snackbar.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
