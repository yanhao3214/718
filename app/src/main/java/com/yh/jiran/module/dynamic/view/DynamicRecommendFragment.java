package com.yh.jiran.module.dynamic.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yh.core.app.BaseFragment;
import com.yh.jiran.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 闫昊
 * @date: 2018/7/25
 * @function: 动态-推荐页
 */
public class DynamicRecommendFragment extends BaseFragment {
    @BindView(R.id.recycler_rec)
    RecyclerView recyclerRec;

    @Override
    protected Object setContent() {
        return R.layout.fragment_dynamic_recommend_layout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_dynamic_recommend_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView(savedInstanceState, mRootView);
        return mRootView;
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {

    }
}
