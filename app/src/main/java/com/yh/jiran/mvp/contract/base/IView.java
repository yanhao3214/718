package com.yh.jiran.mvp.contract.base;

import android.support.annotation.StringRes;

/**
 * @author: 闫昊
 * @date: 2018/7/20
 * @function: View层基本契约
 */
public interface IView {

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
