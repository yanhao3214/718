package com.yh.core.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * @author: 闫昊
 * @date: 2018/7/20
 * @function: 尺寸工具类
 */
public class DimenUtil {
    public static int getScreenWidth(Context context) {
        final Resources resources = context.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        final Resources resources = context.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
