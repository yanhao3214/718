package com.yh.jiran.base.contract;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function: 数据请求回调接口
 */
public interface DataCallback<T> {

    /**
     * 数据请求成功
     * @param t
     */
    void onSuccess(T t);

    /**
     * 解析失败（数据请求成功）
     * @param msg
     */
    void onFailure(String msg);

    /**
     * 数据请求失败
     */
    void onError();

    /**
     * 一次数据请求完成（不论成败）
     */
    void onComplete();
}
