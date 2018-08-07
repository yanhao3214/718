package com.yh.jiran.module.login;

import com.yh.jiran.base.contract.IModel;
import com.yh.jiran.base.contract.IPresenter;
import com.yh.jiran.base.contract.IView;
import com.yh.jiran.module.home.model.entity.HotStar;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function:
 */
public interface LoginContract {
    interface LoginModel extends IModel {

        /**
         * 获取验证码
         */
        Observable<String> getVeriCode(String phone);
    }

    interface RecModel extends IModel {

        /**
         * 获取推荐星球列表
         *
         * @return
         */
        List<HotStar> getRecStar(int page);
    }

    interface InfoModel extends IModel {

        /**
         * 获取推荐星球列表
         */
        String getDefaultName();

        /**
         * 获取：用户名是否重复
         */
        boolean getRepeat(String name);
    }

    interface LoginView extends IView {

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

    interface InfoView extends IView {

        /**
         * 设置默认用户名
         */
        void setDefaultName(String name);

    }

    interface RecView extends IView {

        /**
         * 更新推荐星球列表
         */
        void refreshRecs(List<HotStar> stars);
    }

    interface LoginPresenter extends IPresenter {

        void present();

        void login();
    }

    interface InfoPresenter extends IPresenter {

        /**
         * 验证用户名是否重复
         */
        boolean verifyRepeat(String name);

        /**
         * 初始化
         */
        void present();

    }

    interface RecPresenter extends IPresenter {

        /**
         * 初始化
         */
        void present(int page);
    }
}
