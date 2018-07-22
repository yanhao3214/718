package com.yh.jiran.mvp.contract.base;

import android.support.annotation.StringRes;

/**
 * @author: 闫昊
 * @date: 2018/7/19
 * @function: MVP模式的公共契约
 */
public interface BaseContact {

    /**
     * presenter的基本职责
     */
    interface IPresenter {

        /**
         * 开始的触发
         */
        void start();

        /**
         * 销毁的触发
         */
        void destroy();
    }

    interface IView<T extends IPresenter> {

//        /**
//         * 支持设置一个Presenter
//         * @param presenter
//         */
//        void setPresenter(T presenter);

        /**
         * 显示错误：字符串
         * @param str 错误信息
         */
        void showError(@StringRes int str);

        /**
         * 显示正在加载
         */
        void showLoading();

        /**
         * 显示加载结束
         */
        void hideLoading();
    }
}
