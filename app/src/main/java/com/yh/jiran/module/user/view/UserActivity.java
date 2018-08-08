package com.yh.jiran.module.user.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.utils.Paths;

/**
 * @author: 闫昊
 * @date: 2018/8/7
 * @function: 个人主页
 */
@Route(path = Paths.PATH_USER_HOME_ACTIVITY)
public class UserActivity extends ImmerseActivity {
    public static final String USER_ID = "user_id";


    @Override
    protected int setContent() {
        return R.layout.activity_user_home_layout;
    }
}
