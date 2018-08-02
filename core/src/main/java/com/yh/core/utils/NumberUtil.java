package com.yh.core.utils;

import java.text.DecimalFormat;

/**
 * @author: 闫昊
 * @date: 2018/8/2
 * @function:
 */
public class NumberUtil {
    private static final int POINT_K = 1000;

    public static String parseToK(int num) {
        if (num >= POINT_K) {
            return new DecimalFormat("#.0").format((float) num / 1000)
                    .concat("K");
        }
        return String.valueOf(num).concat("K");
    }
}
