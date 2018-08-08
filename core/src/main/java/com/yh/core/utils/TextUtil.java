package com.yh.core.utils;

import android.graphics.Paint;

/**
 * @author: 闫昊
 * @date: 2018/8/8
 * @function: 文字处理工具类
 */
public class TextUtil {

    /**
     * 计算指定画笔下指定字符串需要的宽度
     */
    public static int getTheTextNeedWidth(Paint thePaint, String text) {
        float[] widths = new float[text.length()];
        thePaint.getTextWidths(text, widths);
        int length = widths.length, nowLength = 0;
        for (float width : widths) {
            nowLength += width;
        }
        return nowLength;
    }
}
