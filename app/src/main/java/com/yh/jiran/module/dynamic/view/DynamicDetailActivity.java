package com.yh.jiran.module.dynamic.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.utils.RouterMap;

/**
 * @author: 闫昊
 * @date: 2018/8/7
 * @function: 动态详情页
 */
@Route(path = RouterMap.PATH_DYNAMIC_DETAIL_ACTIVITY)
public class DynamicDetailActivity extends ImmerseActivity {
    public static final String DYNAMIC_ID = "dynamic_id";


    @Override
    protected int setContent() {
        return R.layout.activity_dynamic_detail_layout;
    }
}
