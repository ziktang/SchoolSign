package com.schoolsign.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by tctctc on 2017/6/24.
 * Function:
 */

public class LessonPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{"已发布","未发布"};

    private List<Fragment> mFragments;

    public LessonPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
    @Override
    public int getCount() {
        return titles.length;
    }
}
