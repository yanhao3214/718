package com.yh.jiran.custom.search;

/**
 * @author: 闫昊
 * @date: 2018/7/27
 * @function:
 */
public interface SearchCallback {

    /**
     * 点击软键盘上“搜索”键的回调接口
     *
     * @param input 搜索框输入内容
     */
    void onSearch(String input);

    /**
     * 点击“取消”按钮的回调接口
     */
    void onCancel();

    /**
     * 点击YSearchEdit“清除”图标的回调接口
     */
    void onClear();
}
