package com.yh.ui.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.widget.EditText;

/**
 * @author: 闫昊
 * @date: 2018/8/4
 * @function: 文字处理工具类
 */
public class TextUtil {

    /**
     * 为EditText设置Hint
     */
    public static void setHint(Context context, EditText edt, String s, int size, int color) {
        SpannableString ss = new SpannableString(s);
        AbsoluteSizeSpan assPhone = new AbsoluteSizeSpan(size, true);
        edt.setHintTextColor(ContextCompat.getColor(context, color));
        ss.setSpan(assPhone, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        edt.setHint(new SpannedString(ss));
    }
}
