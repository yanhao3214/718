package com.yh.jiran.custom.dialog.member.callback;

import android.app.Dialog;

/**
 * @author: 闫昊
 * @date: 2018/8/15
 * @function: 星球资料页-星球成员列表-操作弹窗：禁言的回调接口
 */
public interface MuteClickListener {

    /**
     * 禁言3天
     */
    void temp(Dialog dialog);

    /**
     * 永久禁言
     */
    void forever(Dialog dialog);
}
