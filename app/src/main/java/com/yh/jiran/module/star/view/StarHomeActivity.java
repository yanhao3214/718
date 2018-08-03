package com.yh.jiran.module.star.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.utils.Paths;

/**
 * @author: 闫昊
 * @date: 2018/8/3
 * @function: 星球主页
 */
@Route(path = Paths.PATH_STAR_HOME_ACTIVITY)
public class StarHomeActivity extends ImmerseActivity {
    @Override
    protected int setContent() {
        return R.layout.activity_star_home_layout;
    }
}
