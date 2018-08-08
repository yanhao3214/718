package com.yh.jiran.custom.dialog.callback;

import android.app.Dialog;

/**
 * @author: 闫昊
 * @date: 2018/8/8
 * @function: 动态操作 通用接口回调
 */
public interface CommonCallback {

    /**
     * 删除
     */
    void onDelete(Dialog dialog);

    /**
     * 收藏/取消收藏
     */
    void onCollect(Dialog dialog);

    /**
     * 取消
     */
    void onCancel(Dialog dialog);
}
