package com.yh.jiran.custom.dialog.callback;

import android.app.Dialog;

/**
 * @author: 闫昊
 * @date: 2018/8/8
 * @function: 关注/取消关注 回调接口
 */
public interface ConcernCallback {

    /**
     * 关注/取消关注
     */
    void onConcern(Dialog dialog);
}
