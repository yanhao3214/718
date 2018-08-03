package com.yh.jiran.module.login;

import com.yh.jiran.base.contract.IModel;
import com.yh.jiran.base.contract.IPresenter;
import com.yh.jiran.base.contract.IView;

import io.reactivex.Observable;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function:
 */
public interface LoginContract {
    interface Model extends IModel {

        /**
         * 获取验证码
         */
        Observable<String> getVeriCode(String phone);
    }

    interface View extends IView {

        /**
         * 获取手机号
         */
        String getPhone();

        /**
         * 获取输入的验证码
         */
        String getVeriCode();

        /**
         * 登录
         */
        void login(String phone, String code);
    }

    interface Presenter extends IPresenter {

        void present();

        void login();
    }
}
