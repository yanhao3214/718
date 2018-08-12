package com.yh.jiran.module.user.view;

import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.yh.core.app.BaseFragment;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.module.home.view.adapter.BaseFragmentPagerAdapter;
import com.yh.jiran.utils.Paths;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/8/7
 * @function: 个人信息编辑页
 */
@Route(path = Paths.PATH_USER_EDIT_ACTIVITY)
public class UserEditActivity extends ImmerseActivity {
    public static final String EDIT_USER_ID = "user_id";

    private boolean mOtherView = false;
    private boolean mHasConcern = false;
    private BaseFragmentPagerAdapter mBaseFragmentPagerAdapter;
    private List<BaseFragment> mFragments = new ArrayList<>(3);


    @Override
    protected int setContent() {
        return R.layout.activity_user_edit_layout;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected void initData() {
        super.initData();
    }

}
