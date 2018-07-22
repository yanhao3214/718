package com.yh.jiran.mvp.contract.base;

/**
 * @author: 闫昊
 * @date: 2018/7/20
 * @function: Model层基本契约
 */
public interface IModel<T> {

    /**
     * 获取数据
     *
     * @return 数据
     */
    T getData();
}
