package com.yh.jiran.custom.dialog.comment;

import android.app.Dialog;

/**
 * @author: 闫昊
 * @date: 2018/8/17
 * @function: （动态）评论接口回调
 */
public interface OnCommentClickListener {

    /**
     * 发布评论
     */
    void onPublish(Dialog commentDialog, String comment);
}
