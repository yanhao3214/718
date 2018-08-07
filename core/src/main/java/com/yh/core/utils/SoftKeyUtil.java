package com.yh.core.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/30
 * @function:
 */
public class SoftKeyUtil {

    /**
     * 隐藏软键盘（不适用Fragment）
     */
    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 强制隐藏，单个View，方法有效
     */
    public static void hideSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 强制显示
     */
    public static void showSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            if (!view.isFocused()) {
                view.requestFocus();
            }
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

    /**
     * Activity布局未完全加载完成时，调起软键盘的方法
     */
    public static void showSoftKeyboardDelayed(final Context context, final View view) {
        ((Activity) context).getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                showSoftKeyboard(context, view);
            }
        }, 100);
    }


    /**
     * 批量隐藏输入软键盘
     */
    public static void hideSoftKeyboard(Context context, List<View> viewList) {
        if (viewList == null) {
            return;
        }
        for (View view : viewList) {
            hideSoftKeyboard(context, view);
        }
    }

    /**
     * 逆转输入法状态（若输入法在窗口上已显示则隐藏，反之则显示）
     */
    public static void toggleSoftKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static boolean getKeyboardState(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            throw new RuntimeException("InputMethodManager is null !");
        }
        return imm.isActive();
    }
}
