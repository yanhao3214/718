package com.yh.jiran.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author: 闫昊
 * @date: 2018/8/6
 * @function:
 */
public class ScrollerGridView extends GridView {
    public ScrollerGridView(Context context) {
        super(context);
    }

    public ScrollerGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollerGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
