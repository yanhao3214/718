package com.yh.jiran.module.dynamic.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.utils.RouterMap;

/**
 * @author: 闫昊
 * @date: 2018/8/14
 * @function: 动态发布页
 */
@Route(path = RouterMap.PATH_DYNAMIC_TWEET_ACTIVITY)
public class DynamicTweetActivity extends ImmerseActivity {

    @Override
    protected int setContent() {
        return R.layout.activity_dynamic_tweet_layout;
    }

    @Override
    protected void initView() {
        super.initView();

    }
}
