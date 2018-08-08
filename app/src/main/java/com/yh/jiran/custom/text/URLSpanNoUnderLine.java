package com.yh.jiran.custom.text;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.style.URLSpan;

import com.yh.jiran.R;

/**
 * @author: 闫昊
 * @date: 2018/8/8
 * @function: 无下划线、自定义颜色超链接
 */
public class URLSpanNoUnderLine extends URLSpan {
    private Context mContext;

    public URLSpanNoUnderLine(Context context, String url) {
        super(url);
        mContext = context;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
        ds.setColor(ContextCompat.getColor(mContext, R.color.text_blue));
    }
}
