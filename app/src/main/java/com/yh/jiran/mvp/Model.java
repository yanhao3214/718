package com.yh.jiran.mvp;

/**
 * @author: 闫昊
 * @date: 2018/7/19
 * @function:
 */
public class Model implements IModel {
    @Override
    public String search(int code) {
        return "Result found ----> User: " + code;
    }
}
