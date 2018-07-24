package com.yh.jiran.module.search.view;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.app.BaseActivity;
import com.yh.jiran.R;
import com.yh.jiran.module.search.SearchContract;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function: 搜索界面
 */
public class SearchActivity extends BaseActivity implements SearchContract.View{
    @Override
    protected int setContent() {
        return R.layout.activity_search_layout;
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    @Override
    public void showError(int str) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
