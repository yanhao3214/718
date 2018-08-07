package com.yh.jiran.module.login.model;

import com.yh.jiran.module.login.LoginContract;

import java.util.Random;

/**
 * @author: 闫昊
 * @date: 2018/8/4
 * @function: 确定头像和用户名Model
 */
public class InfoModel implements LoginContract.InfoModel {

    @Override
    public String getDefaultName() {
        return "手机用户w" + new Random().nextInt(1000) + "v";
    }

    @Override
    public boolean getRepeat(String name) {
        return false;
    }

    @Override
    public Object getData() {
        return null;
    }
}
