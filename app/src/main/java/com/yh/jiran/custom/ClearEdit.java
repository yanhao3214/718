package com.yh.jiran.custom;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.yh.jiran.R;
import com.yh.jiran.custom.search.ClearEditCallback;

/**
 * @author: 闫昊
 * @date: 2018/8/4
 * @function:
 */
public class ClearEdit extends android.support.v7.widget.AppCompatEditText {
    private Drawable clearDrawable;
    private ClearEditCallback mCallback;

    public ClearEdit(Context context) {
        super(context);
        init();
    }

    public ClearEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        clearDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_search_input_clear);

    }

    /**
     * 通过监听复写EditText本身的方法来确定是否显示删除图标
     * 监听方法：onTextChanged（） & onFocusChanged（）
     * 调用时刻：当输入框内容变化时 & 焦点发生变化时
     */
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setClearIconVisible(hasFocus() && text.length() > 0);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setClearIconVisible(focused && length() > 0);
    }

    /**
     * 是否显示右侧删除图标
     */
    private void setClearIconVisible(boolean visible) {
        setCompoundDrawablesWithIntrinsicBounds(null,
                null, visible ? clearDrawable : null, null);
    }

    /**
     * 对删除图标区域设置点击事件，即"点击 = 清空搜索框内容"
     * 原理：当手指抬起的位置在删除图标的区域，即视为点击了删除图标 = 清空搜索框内容
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Drawable drawable = clearDrawable;
                if (drawable != null &&
                        event.getX() >= getWidth() - getPaddingRight() - drawable.getBounds().width()
                        && event.getX() <= getWidth() - getPaddingRight()) {
                    setText("");
                    if (mCallback != null) {
                        mCallback.onClear();
                    }
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setClearCallback(ClearEditCallback clearCallback) {
        mCallback = clearCallback;
    }
}
