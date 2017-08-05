package com.schoolsign.teacher.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.schoolsign.R;
import com.schoolsign.adapter.LessonPagerAdapter;
import com.schoolsign.app.Manager;
import com.schoolsign.base.BaseToolBarActivity;
import com.schoolsign.user.bean.Course;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.R.attr.fragment;

public class TeaCouseActivity extends BaseToolBarActivity {

    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.lesson_pager)
    ViewPager mLessonPager;

    private List<Fragment> mFragments;

    private Course mCourse;

    @Override
    protected void initView(View contextView) {
        mCourse = Manager.getCourse();
        setCenterTitle(mCourse.getCname());

        mFragments = new ArrayList<>();
        mFragments.add(LessonPublishFragment.getInstance());
        mFragments.add(LessonUnPublishFragment.getInstance());
        mLessonPager.setAdapter(new LessonPagerAdapter(getSupportFragmentManager(), mFragments));
        mTab.setupWithViewPager(mLessonPager);
    }


    @Override
    protected void onResume() {
        super.onResume();


        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        if (fragments != null) {
            LessonPublishFragment fragment = (LessonPublishFragment) getSupportFragmentManager().getFragments().get(0);
            LessonUnPublishFragment fragment2 = (LessonUnPublishFragment) getSupportFragmentManager().getFragments().get(1);
            if (fragment != null) {
                fragment.refresh();
            }
            if (fragment2 != null) {
                fragment2.refresh();
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tea_couse;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.allStudent:
                startActivity(AllStudentActivity.class);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stu_course_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @OnClick(R.id.add)
    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                startActivity(AddLessonActivity.class);
                break;
        }
    }
}
