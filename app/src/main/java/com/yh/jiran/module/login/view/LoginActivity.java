package com.yh.jiran.module.login.view;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.app.BaseActivity;
import com.yh.jiran.R;
import com.yh.jiran.module.login.LoginContract;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function:
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {
    @Override
    protected int setContent() {
        return R.layout.activity_login_layout;
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
