package com.yh.jiran.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @author: 闫昊
 * @date: 2018/7/27
 * @function:
 */
public class ScrollerListView extends ListView {

    public ScrollerListView(Context context) {
        super(context);
    }

    public ScrollerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollerListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
