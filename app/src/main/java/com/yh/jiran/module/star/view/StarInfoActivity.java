package com.yh.jiran.module.star.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.utils.RouterMap;

/**
 * @author: 闫昊
 * @date: 2018/8/14
 * @function: 星球资料页
 */
@Route(path = RouterMap.PATH_STAR_INFO_ACTIVITY)
public class StarInfoActivity extends ImmerseActivity {

    @Override
    protected int setContent() {
        return R.layout.activity_star_info_layout;
    }

    @Override
    protected void initView() {
        super.initView();

    }
}
