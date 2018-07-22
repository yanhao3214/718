package com.yh.jiran.module.test;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yh.core.app.BaseActivity;
import com.yh.jiran.R;

/**
 * @author: 闫昊
 * @date: 2018/7/21
 * @function:
 */
@Route(path = "/com/URLActivity1", group = "test")
public class UrlReceiveActivity1 extends BaseActivity {
    @Override
    protected int setContent() {
        return R.layout.activity_courier_layout;
    }
}
