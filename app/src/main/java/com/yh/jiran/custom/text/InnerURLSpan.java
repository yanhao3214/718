package com.yh.jiran.custom.text;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yh.jiran.R;
import com.yh.jiran.base.WebViewActivity;
import com.yh.jiran.utils.Paths;

/**
 * @author: 闫昊
 * @date: 2018/8/9
 * @function: 内部跳转的超链接：无下划线、文本蓝
 */
public class InnerURLSpan extends ClickableSpan {

    private Context mContext;
    private String mTitle;
    private String mUrl;

    public InnerURLSpan(Context context, String title, String url) {
        mContext = context;
        mTitle = title;
        mUrl = url;
    }

    @Override
    public void onClick(View view) {
        ARouter.getInstance()
                .build(Paths.PATH_WEBVIEW_ACTIVITY)
                .withString(WebViewActivity.WEBVIEW_TITLE, mTitle)
                .withString(WebViewActivity.WEBVIEW_URL, mUrl)
                .navigation();
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
        ds.setColor(ContextCompat.getColor(mContext, R.color.text_blue));
    }
}
