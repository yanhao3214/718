package com.yh.jiran.module.home.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yh.core.app.BaseFragment;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/26
 * @function:
 */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTabs;
    private List<BaseFragment> mFragments;

    public BaseFragmentPagerAdapter(FragmentManager fm, String[] mTabs, List<BaseFragment> mFragments) {
        super(fm);
        this.mTabs = mTabs;
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mTabs.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }
}
