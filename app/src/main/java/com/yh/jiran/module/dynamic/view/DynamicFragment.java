package com.yh.jiran.module.dynamic.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yh.core.app.BaseFragment;
import com.yh.jiran.R;
import com.yh.jiran.base.HomeActivity;
import com.yh.jiran.module.home.view.adapter.BaseFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 闫昊
 * @date: 2018/7/25
 * @function: 动态页面
 */
public class DynamicFragment extends BaseFragment {

    private HomeActivity mActivity;
    private BaseFragmentPagerAdapter mBaseFragmentPagerAdapter;
    private List<BaseFragment> mFragments = new ArrayList<>(2);

    @BindView(R.id.tab_dynamic)
    TabLayout tabDynamic;
    @BindView(R.id.iv_edit)
    AppCompatImageView ivEdit;
    @BindView(R.id.pager_dynamic)
    ViewPager pagerDynamic;
    @BindArray(R.array.tab_dynamic)
    String[] mTabs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_dynamic_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView(savedInstanceState, mRootView);
        return mRootView;
    }

    @Override
    protected Object setContent() {
        return R.layout.fragment_dynamic_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        mFragments.add(new DynamicConcernFragment());
        mFragments.add(new DynamicRecommendFragment());
        mActivity = (HomeActivity) getActivity();
        if (mActivity != null) {
            mBaseFragmentPagerAdapter = new BaseFragmentPagerAdapter
                    (mActivity.getSupportFragmentManager(), mTabs, mFragments);
        }
        pagerDynamic.setAdapter(mBaseFragmentPagerAdapter);
        tabDynamic.setupWithViewPager(pagerDynamic);
    }

    @Override
    protected void initData() {
    }
}
