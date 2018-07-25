package com.yh.jiran.base;

import android.net.Uri;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yh.core.app.BaseActivity;

/**
 * @author: 闫昊
 * @date: 2018/7/21
 * @function: 页面跳转中介
 */
public class UrlReceiveActivity extends BaseActivity {
    @Override
    protected int setContent() {
        return 0;
    }

    @Override
    protected void initData() {
        Uri uri = getIntent().getData();
        if (uri != null) {
            ARouter.getInstance().build(uri).navigation(this, new NavCallback() {
                @Override
                public void onArrival(Postcard postcard) {
                    finish();
                }
            });
        }
    }
}
