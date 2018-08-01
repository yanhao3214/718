package com.yh.jiran.module.search.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yh.core.app.BaseFragment;
import com.yh.jiran.R;
import com.yh.jiran.module.home.view.adapter.BaseFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 闫昊
 * @date: 2018/7/231
 * @function: 搜索结果
 */
public class SearchFragment extends BaseFragment {

    private SearchActivity mActivity;
    private BaseFragmentPagerAdapter mBaseFragmentPagerAdapter;
    private List<BaseFragment> mFragments = new ArrayList<>(3);

    @BindView(R.id.tab_result)
    TabLayout tabResult;
    @BindView(R.id.pager_result)
    ViewPager pagerResult;
    @BindArray(R.array.tab_search)
    String[] mTabs;

    @Override
    protected Object setContent() {
        return R.layout.fragment_search_layout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_search_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView(savedInstanceState, mRootView);
        return mRootView;
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        mFragments.add(new SearchDynamicFragment());
        mFragments.add(new SearchStarFragment());
        mFragments.add(new SearchUserFragment());
        mActivity = (SearchActivity) getActivity();
        if (mActivity != null) {
            mBaseFragmentPagerAdapter = new BaseFragmentPagerAdapter
                    (mActivity.getSupportFragmentManager(), mTabs, mFragments);
        }
        pagerResult.setAdapter(mBaseFragmentPagerAdapter);
        tabResult.setupWithViewPager(pagerResult);
    }

    @Override
    protected void initData() {
    }
}
