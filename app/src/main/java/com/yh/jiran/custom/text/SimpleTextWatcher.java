package com.yh.jiran.custom.text;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @author: 闫昊
 * @date: 2018/8/18
 * @function: 简化版TextWatcher，只留onTextChanged，使写法更简洁（RxJava中调用）
 */
public abstract class SimpleTextWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
