package com.yh.jiran.module.login.model;

import com.yh.jiran.module.login.LoginContract;

import io.reactivex.Observable;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function: 手机验证码登录Model
 */
public class LoginModel implements LoginContract.LoginModel {
    @Override
    public Object getData() {
        return null;
    }

    @Override
    public Observable<String> getVeriCode(String phone) {
        return null;
    }

}
