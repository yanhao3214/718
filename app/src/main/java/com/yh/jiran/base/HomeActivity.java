package com.yh.jiran.base;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.WindowManager;
import android.widget.RadioButton;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yh.core.app.BaseFragment;
import com.yh.core.utils.SoftKeyUtil;
import com.yh.jiran.R;
import com.yh.jiran.module.dynamic.view.DynamicFragment;
import com.yh.jiran.module.home.view.HomeFragment;
import com.yh.jiran.module.Message.view.MessageFragment;
import com.yh.jiran.module.user.view.UserFragment;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnCheckedChanged;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function: 主页，承载4个Fragment
 */
public class HomeActivity extends ImmerseActivity {

    private HomeFragment mHomeFragment = null;
    private DynamicFragment mDynamicFragment = null;
    private MessageFragment mMessageFragment = null;
    private UserFragment mUserFragment = null;
    private RxFragment mFormerFragment = null;

    @BindView(R.id.rb_home)
    RadioButton rbHome;

    @BindColor(R.color.textChosen)
    int colorChosen;

    @BindColor(R.color.textGrey)
    int colorNormal;

    @Override
    protected int setContent() {
        return R.layout.activity_home_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        mHomeFragment = new HomeFragment();
        mFormerFragment = mHomeFragment;
        selectHome();
    }

    @OnCheckedChanged(R.id.rb_home)
    public void onHomeCheckedChanged(RadioButton home, boolean isChecked) {
        if (isChecked) {
            selectHome();
        } else {
            home.setTextColor(colorNormal);
            home.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.img_edit, 0, 0);
        }
    }

    @OnCheckedChanged(R.id.rb_dynamic)
    public void onDynamicCheckedChanged(RadioButton dynamic, boolean isChecked) {
        if (isChecked) {
            dynamic.setTextColor(colorChosen);
            dynamic.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.img_edit, 0, 0);
            if (mDynamicFragment == null) {
                mDynamicFragment = new DynamicFragment();
            }
            changeFragment(mDynamicFragment);
        } else {
            dynamic.setTextColor(colorNormal);
            dynamic.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.img_tab_dynamic_normal, 0, 0);
        }
    }

    @OnCheckedChanged(R.id.rb_message)
    public void onMessageCheckedChanged(RadioButton message, boolean isChecked) {
        if (isChecked) {
            message.setTextColor(colorChosen);
            message.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.img_edit, 0, 0);
            if (mMessageFragment == null) {
                mMessageFragment = new MessageFragment();
            }
            changeFragment(mMessageFragment);
        } else {
            message.setTextColor(colorNormal);
            message.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.img_tab_message_normal, 0, 0);
        }
    }

    @OnCheckedChanged(R.id.rb_user)
    public void onUserCheckedChanged(RadioButton user, boolean isChecked) {
        if (isChecked) {
            user.setTextColor(colorChosen);
            user.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.img_tab_user_normal, 0, 0);
            if (mUserFragment == null) {
                mUserFragment = new UserFragment();
            }
            changeFragment(mUserFragment);
        } else {
            user.setTextColor(colorNormal);
            user.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.img_edit, 0, 0);
        }
    }

    /**
     * 选中首页
     */
    private void selectHome() {
        rbHome.setTextColor(colorChosen);
        rbHome.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.img_tab_home_selected, 0, 0);
        changeFragment(mHomeFragment);
    }

    /**
     * 切换主Tab页面，隐藏上一个选中的Tab页，显示当前选中的Tab页
     *
     * @param fragment
     */
    private void changeFragment(RxFragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (mFormerFragment.isAdded()) {
            transaction.hide(mFormerFragment);
        }
        if (!fragment.isAdded()) {
            transaction.add(R.id.layout_content, fragment);
        }
        transaction.show(fragment);
        mFormerFragment = fragment;
        transaction.commit();
    }
}
