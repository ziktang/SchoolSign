package com.schoolsign.user.view;

import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.schoolsign.R;
import com.schoolsign.app.NetCons;
import com.schoolsign.app.RetrofitClient;
import com.schoolsign.base.BaseActivity;
import com.schoolsign.base.BaseAdapter;
import com.schoolsign.base.BaseToolBarActivity;
import com.schoolsign.common.retrofit.HttpResult;
import com.schoolsign.common.rx2.RxSchedulers;
import com.schoolsign.user.bean.SchoolResult;
import com.schoolsign.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class SchoolActivity extends BaseToolBarActivity {

    //权限请求码
    private static final int PERMISSON_REQUESTCODE = 1;
    //定位配置对象
    public AMapLocationClientOption mLocationOption = null;
    //定位执行类
    private AMapLocationClient mlocationClient;
    //定位监听
    private AMapLocationListener locationListener;


    @BindView(R.id.et_school)
    EditText mEtSchool;

    @BindView(R.id.school_list)
    RecyclerView mSchools;


    @BindView(R.id.near_school)
    TextView mTvNearSchool;

    private List<SchoolResult> mNearSchools;

    private List<SchoolResult> mSchoolResults;

    private BaseAdapter mAdapter;

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };


    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    @Override
    protected void onResume() {
        super.onResume();
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }

        if (findDeniedPermissions(needPermissions).size() == 0) {
            initLocation();
        } else {
            mTvNearSchool.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void initView(View contextView) {
        setCenterTitle("选择学校");
        mSchoolResults = new ArrayList<>();
        mNearSchools = new ArrayList<>();
        RxTextView.textChanges(mEtSchool)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
                        if (charSequence.toString().length() == 0) {
                            mSchoolResults.clear();
                            mSchoolResults.addAll(mNearSchools);
                            mAdapter.notifyDataSetChanged();
                            return false;
                        }
                        return true;
                    }
                })
                .observeOn(Schedulers.io())
                .switchMap(new Function<CharSequence, Observable<HttpResult<List<SchoolResult>>>>() {
                    @Override
                    public Observable<HttpResult<List<SchoolResult>>> apply(CharSequence charSequence) throws Exception {
                        return RetrofitClient.getService().getSearchSchool(charSequence.toString());
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<SchoolResult>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mRxManager.add(d);
                    }

                    @Override
                    public void onNext(HttpResult<List<SchoolResult>> httpResult) {
                        List<SchoolResult> results = httpResult.getResult();
                        if (results != null) {
                            mSchoolResults.clear();
                            mSchoolResults.addAll(results);
                            mAdapter.notifyDataSetChanged();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "00000 onComplete");
                    }
                });
        mAdapter = new BaseAdapter(this, R.layout.school_list_item, mSchoolResults, new BaseAdapter.OnItem() {
            @Override
            public void onClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("school", mSchoolResults.get(position));
                Intent intent = new Intent(SchoolActivity.this, RegisterActivity.class);
//                intent.setFlags(PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);
//                intent.putExtra("bundle",bundle);
                intent.replaceExtras(bundle);
                startActivity(intent);
                finish();
            }

            @Override
            public void onLongClick(int position) {

            }

            @Override
            public void onBind(BaseAdapter.BaseViewHolder mHolder, int position) {
                mHolder.setText(R.id.schoolName, mSchoolResults.get(position).getSchoolName());
            }
        });
        mSchools.setAdapter(mAdapter);
        mSchools.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initLocation() {
        mlocationClient = new AMapLocationClient(this.getApplicationContext());
        //获取定位配置
        mLocationOption = getDefaultOption();
        //设置定位配置
        mlocationClient.setLocationOption(mLocationOption);
        //设置定位监听器
        locationListener = new MyAMapLocationListener();
        mlocationClient.setLocationListener(locationListener);
        mlocationClient.startLocation();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_school;
    }

    @Override
    public void onViewClick(View view) {
    }

    public AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
//        mOption.setGpsFirst(true);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
//        mOption.setHttpTimeOut(10000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
//        mOption.setInterval(10000);//可选，设置定位间隔。默认为2秒
//        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
//        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
//        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
//        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
//        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }


    private void destroyLocation() {
        if (null != mlocationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            mlocationClient.onDestroy();
            mlocationClient = null;
            mlocationClient = null;
        }
    }

    /**
     * @param permissions
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton(R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    class MyAMapLocationListener implements AMapLocationListener {
        private static final String TAG = "SchoolActivity";

        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");
                    //定位完成的时间
                    sb.append("定位时间: " + Utils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");


                    RetrofitClient.getService()
                            .getNearSchool(location.getCity())
                            .compose(RxSchedulers.<HttpResult<List<SchoolResult>>>ioMain())
                            .subscribe(new Observer<HttpResult<List<SchoolResult>>>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    mRxManager.add(d);
                                }

                                @Override
                                public void onNext(HttpResult<List<SchoolResult>> httpResult) {
                                    Log.i(TAG, "onNext");
                                    List<SchoolResult> results = httpResult.getResult();
                                    if (results != null) {
                                        mNearSchools.clear();
                                        mNearSchools.addAll(results);

                                        mSchoolResults.clear();
                                        mSchoolResults.addAll(results);
                                        mAdapter.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                }

                                @Override
                                public void onComplete() {
                                    Log.i(TAG, "111  onComplete");
                                }
                            });
                } else {
                    Toast.makeText(SchoolActivity.this, "定位失败，请检查相关权限设置", Toast.LENGTH_SHORT).show();
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                }
                //定位之后的回调时间
                sb.append("回调时间: " + Utils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");
                //解析定位结果，
                Log.i(TAG, sb.toString());
            } else {
                Toast.makeText(SchoolActivity.this, "定位失败，请检查相关权限设置", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "定位失败，location is null");
            }
        }
    }
}

