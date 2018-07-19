package com.yh.jiran.mvp;

/**
 * @author: 闫昊
 * @date: 2018/7/19
 * @function:
 */
public interface IView {
    /**
     * 获取输入信息
     * @return 输入的字符串
     */
    String getInput();

    /**
     * 输出结果
     * @param result 结果参数
     */
    void setResult(String result);

}
