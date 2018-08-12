package com.yh.jiran.module.user.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yh.core.app.BaseFragment;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.module.dynamic.view.DynamicConcernFragment;
import com.yh.jiran.module.home.view.adapter.BaseFragmentPagerAdapter;
import com.yh.jiran.utils.Paths;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/8/7
 * @function: 个人主页
 */
@Route(path = Paths.PATH_USER_HOME_ACTIVITY)
public class UserActivity extends ImmerseActivity {
    public static final String USER_ID = "user_id";
    public static final String USER_OTHER_VIEW = "other_view";
    public static final String USER_HAS_COLLECT = "has_collect";

    private boolean mOtherView = false;
    private boolean mHasConcern = false;
    private BaseFragmentPagerAdapter mBaseFragmentPagerAdapter;
    private List<BaseFragment> mFragments = new ArrayList<>(3);

    @BindView(R.id.tab_user)
    TabLayout tabUser;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.btn_edit)
    AppCompatButton btnEdit;
    @BindArray(R.array.tab_user)
    String[] mTabs;


    @Override
    protected int setContent() {
        return R.layout.activity_user_test1_layout;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        mOtherView = bundle.getBoolean(USER_OTHER_VIEW, false);
        mHasConcern = bundle.getBoolean(USER_HAS_COLLECT, false);
        return true;
    }

    @Override
    protected void initView() {
        super.initView();

        btnEdit.setText(mOtherView ? (mHasConcern ? "+关注" : "已关注") : "编辑");
        btnEdit.setTextColor(ContextCompat.getColor(this, mOtherView ?
                (mHasConcern ? R.color.colorGrey2 : R.color.text_blue) : R.color.colorGrey2));
        btnEdit.setBackgroundResource(mOtherView ? (mHasConcern ? R.drawable.bg_discover_join_done
                : R.drawable.bg_discover_join_normal) : R.drawable.bg_discover_join_done);

        DynamicConcernFragment dynamicFragment = new DynamicConcernFragment();
        DynamicConcernFragment dynamicCollect = new DynamicConcernFragment();
        dynamicCollect.setSelfCollect(true);
        mFragments.add(dynamicFragment);
        mFragments.add(new UserStarsFragment());
        mFragments.add(dynamicCollect);
        mBaseFragmentPagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), mTabs, mFragments);
        viewPager.setAdapter(mBaseFragmentPagerAdapter);
        tabUser.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick(R.id.btn_edit)
    public void onEdit() {
        ARouter.getInstance()
                .build(Paths.PATH_USER_EDIT_ACTIVITY)
                .navigation();
    }
}
