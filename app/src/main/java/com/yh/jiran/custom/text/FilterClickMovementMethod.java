package com.yh.jiran.custom.text;

import android.text.Layout;
import android.text.Spannable;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @author: 闫昊
 * @date: 2018/8/9
 * @function: 点击事件过滤器（点击超链接，则父TextView不再响应点击事件）
 */
public class FilterClickMovementMethod implements View.OnTouchListener {
    private static FilterClickMovementMethod sInstance;

    public static FilterClickMovementMethod getInstance() {
        if (sInstance == null) {
            sInstance = new FilterClickMovementMethod();
        }
        return sInstance;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean flag = false;
        TextView widget = (TextView) v;
        CharSequence text = widget.getText();
        Spannable spannable = Spannable.Factory.getInstance().newSpannable(text);
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            x -= widget.getPaddingLeft();
            y -= widget.getPaddingTop();
            x += widget.getScrollX();
            y += widget.getScrollY();
            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);
            ClickableSpan[] link = spannable.getSpans(off, off, ClickableSpan.class);
            if (link.length != 0) {
                if (action == MotionEvent.ACTION_UP) {
                    link[0].onClick(widget);
                }
                flag = true;
            }
        }
        return flag;
    }
}
