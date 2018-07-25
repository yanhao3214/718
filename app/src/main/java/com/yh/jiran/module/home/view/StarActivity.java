package com.yh.jiran.module.home.view;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.app.BaseActivity;
import com.yh.jiran.module.home.HomeMineContract;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function: 星球页面
 */
public class StarActivity extends BaseActivity implements HomeMineContract.View {
    @Override
    protected int setContent() {
        return 0;
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    @Override
    public void showError(int str) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
