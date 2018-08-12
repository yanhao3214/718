package com.yh.jiran.module.search.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yh.core.app.BaseFragment;
import com.yh.jiran.R;

import butterknife.ButterKnife;

/**
 * @author: 闫昊
 * @date: 2018/7/31
 * @function: 搜索结果-星球
 */
public class SearchStarFragment extends BaseFragment {

    //    private List<HomeStar> mStars = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_search_star_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView(savedInstanceState, mRootView);
        return mRootView;
    }

    @Override
    protected Object setContent() {
        return R.layout.fragment_search_star_layout;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
    }
}
