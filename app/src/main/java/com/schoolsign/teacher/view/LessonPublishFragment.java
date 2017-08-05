package com.schoolsign.teacher.view;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.view.menu.ShowableListMenu;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.schoolsign.R;
import com.schoolsign.adapter.TeaLessonAdapter;
import com.schoolsign.app.Manager;
import com.schoolsign.app.NetCons;
import com.schoolsign.app.RetrofitClient;
import com.schoolsign.base.BasePageFragment;
import com.schoolsign.common.retrofit.HttpResult;
import com.schoolsign.common.rx2.RxSchedulers;
import com.schoolsign.user.bean.Lesson;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableObserver;

import static android.R.attr.action;

public class LessonPublishFragment extends BasePageFragment implements SwipeRefreshLayout.OnRefreshListener {

    public static LessonPublishFragment getInstance() {
        LessonPublishFragment fragment = new LessonPublishFragment();
        return fragment;
    }

    @BindView(R.id.lesson_List)
    EasyRecyclerView mLessonList;
    @BindView(R.id.refresh_layouot)
    SwipeRefreshLayout mRefreshLayouot;
    private TeaLessonAdapter mAdapter;

    @Override
    protected void initView() {
        mAdapter = new TeaLessonAdapter(getContext(),true);
        mLessonList.setAdapter(mAdapter);
        mLessonList.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(),TeacherSignActivity.class);
                intent.putExtra("lesson",mAdapter.getAllData().get(position));
                startActivity(intent);
            }
        });
        mRefreshLayouot.setOnRefreshListener(this);
        mRefreshLayouot.setColorSchemeResources(android.R.color.holo_blue_bright, android
                .R.color.holo_orange_light, android.R.color.holo_green_light, android.R.color
                .holo_red_dark);
    }

    public void refresh(){
        getData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_lesson;
    }

    @Override
    public boolean fetchData() {
        getData();
        return true;
    }

    @Override
    public void onRefresh() {
        getData();
    }

    private void getData() {

        RetrofitClient.getService().teaAllLesson(Manager.getCid(), NetCons.FINISHED)
                .compose(RxSchedulers.<HttpResult<List<Lesson>>>ioMain())
                .subscribe(new Observer<HttpResult<List<Lesson>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mRxManager.add(d);
                    }

                    @Override
                    public void onNext(HttpResult<List<Lesson>> httpResult) {
                        Log.i("AAA", "onNext");
                        Log.i("AAA", "result:" + httpResult.toString());
                        switch (httpResult.getCode()) {
                            case NetCons.SYSTEM_ERROR:
                                Snackbar.make(mLessonList, "网络好像出了点问题", Snackbar.LENGTH_SHORT).show();
                                break;
                            case NetCons.SECCESS:
                                mAdapter.clear();
                                mAdapter.addAll(httpResult.getResult());
                                mAdapter.notifyDataSetChanged();
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRefreshLayouot.setRefreshing(false);
                        Log.i("AAA", "onError");
                        e.printStackTrace();

                    }

                    @Override
                    public void onComplete() {
                        mRefreshLayouot.setRefreshing(false);
                        Log.i("AAA", "onComplete");
                    }
                });
    }


}
