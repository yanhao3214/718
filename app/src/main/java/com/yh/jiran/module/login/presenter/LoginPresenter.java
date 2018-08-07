package com.yh.jiran.module.login.presenter;

import com.yh.jiran.module.login.LoginContract;
import com.yh.jiran.module.login.model.LoginModel;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function:
 */
public class LoginPresenter implements LoginContract.LoginPresenter {
    private LoginContract.LoginView mView;
    private LoginContract.LoginModel mModel;

    public LoginPresenter(LoginContract.LoginView view) {
        this.mView = view;
        mModel = new LoginModel();
    }

    @Override
    public void present() {

    }

    @Override
    public void login() {

    }
}
