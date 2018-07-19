package com.yh.core.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: 闫昊
 * @date: 2018/7/19
 * @function:
 */
public abstract class BaseFragment extends Fragment {
    protected View mRootView;
    protected Unbinder mUnbinder;

    /**
     * 设置布局资源
     *
     * @return 布局ID
     */
    protected abstract int setContent();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            int layoutId = setContent();
            mRootView = inflater.inflate(layoutId, container, false);
            initView(mRootView);
        } else if (mRootView.getParent() != null) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
        return mRootView;
    }

    /**
     * 布局加载完成后初始化数据、findViewById，避免页面切换卡顿
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    /**
     * 初始化控件
     *
     * @param rootView
     */
    protected void initView(View rootView) {
        mUnbinder = ButterKnife.bind(this, rootView);
    }

    /**
     * 处理传给当前Fragment的参数
     *
     * @param bundle
     */
    protected void initArgs(Bundle bundle) {
    }

    /**
     * @return true表示拦截返回逻辑，fragment返回上层、Activity不销毁
     * false表示不拦截返回逻辑，Activity自行处理返回逻辑（销毁）
     */
    protected boolean onBackPressed() {
        return false;
    }
}
