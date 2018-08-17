package com.yh.jiran.custom.text;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.yh.jiran.R;

/**
 * @author: 闫昊
 * @date: 2018/8/16
 * @function: 自定义内部跳转的超链接：无下划线、文本蓝
 */
public class SmartSpan extends ClickableSpan {
    private Context mContext;
    private OnSmartSpanCallback mOnSmartSpanCallback;

    public SmartSpan(Context context, OnSmartSpanCallback onSmartSpanCallback) {
        mContext = context;
        mOnSmartSpanCallback = onSmartSpanCallback;
    }

    @Override
    public void onClick(View widget) {
        mOnSmartSpanCallback.jump();
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
        ds.setColor(ContextCompat.getColor(mContext, R.color.text_blue));
    }
}
