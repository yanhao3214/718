package com.yh.jiran.module.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yh.core.app.BaseFragment;
import com.yh.jiran.R;
import com.yh.jiran.base.HomeActivity;
import com.yh.jiran.module.home.view.adapter.BaseFragmentPagerAdapter;
import com.yh.jiran.utils.RouterMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/7/25
 * @function: 首页Fragment
 */
public class HomeFragment extends BaseFragment {

    private HomeActivity mActivity;
    private BaseFragmentPagerAdapter mBaseFragmentPagerAdapter;
    private List<BaseFragment> mFragments = new ArrayList<>(2);

    @BindView(R.id.tab_home)
    TabLayout tabHome;
    @BindView(R.id.pager_home)
    ViewPager pagerHome;
    @BindArray(R.array.tab_home)
    String[] mTabs;

    @OnClick(R.id.iv_edit)
    public void pickStar() {
//        ARouter.getInstance().build(RouterMap.PATH_STAR_PICK_ACTIVITY).navigation();
        ARouter.getInstance().build(RouterMap.PATH_DYNAMIC_TWEET_ACTIVITY).navigation();
    }

    @Override
    protected Object setContent() {
        return R.layout.fragment_home_layout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView(savedInstanceState, mRootView);
        return mRootView;
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        mFragments.add(new HomeMineFragment());
        mFragments.add(new HomeDiscoverFragment());
        mActivity = (HomeActivity) getActivity();
        if (mActivity != null) {
            mBaseFragmentPagerAdapter = new BaseFragmentPagerAdapter
                    (mActivity.getSupportFragmentManager(), mTabs, mFragments);
        }
        pagerHome.setAdapter(mBaseFragmentPagerAdapter);
        tabHome.setupWithViewPager(pagerHome);
    }

    @Override
    protected void initData() {
    }
}
