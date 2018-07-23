package com.yh.core.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @author: 闫昊
 * @date: 2018/7/19
 * @function:
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    /**
     * 设置布局资源
     *
     * @return 布局ID
     */
    protected abstract int setContent();
    // TODO: 2018/7/19  重载View

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();

        if (initArgs(getIntent().getExtras())) {
            int layoutId = setContent();
            setContentView(layoutId);
            initView();
            initData();
        } else {
            finish();
        }
    }

    /**
     * 初始化窗口
     */
    protected void initWindow() {
    }

    /**
     * 处理传给当前Activity的参数
     *
     * @param bundle 参数bundle
     * @return 参数正确返回true，参数错误返回false
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    /**
     * 初始化控件
     */
    protected void initView() {
        ButterKnife.bind(this);
    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    /**
     * 点击界面导航返回时，finish当前页面
     *
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    /**
     * 先返回最上层Fragment，再销毁Activity
     */
    @Override
    public void onBackPressed() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof BaseFragment) {
                    //若拦截返回事件则直接return
                    if (((BaseFragment) fragment).onBackPressed()) {
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
        finish();
    }
}
