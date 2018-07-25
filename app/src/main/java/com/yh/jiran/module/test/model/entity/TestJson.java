package com.yh.jiran.module.test.model.entity;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function:
 */
public class TestJson {
    public String msg;
    public int code;
    public TestData data;

    public class TestData {
        public String extPubKey;
        public String password;
        public String mobile;
    }
}
