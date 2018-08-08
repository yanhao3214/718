package com.yh.jiran.custom.dialog.callback;

import android.app.Dialog;

/**
 * @author: 闫昊
 * @date: 2018/8/8
 * @function: 禁言/解除禁言 回调接口
 */
public interface MuteCallback {

    /**
     * 禁言3天
     */
    void onMuteTemp(Dialog dialog);

    /**
     * 永久禁言
     */
    void onMuteForever(Dialog dialog);

    /**
     * 解除禁言
     */
    void onCancel(Dialog dialog);
}
