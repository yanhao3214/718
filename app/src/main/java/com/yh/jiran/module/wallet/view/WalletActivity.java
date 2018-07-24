package com.yh.jiran.module.wallet.view;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.app.BaseActivity;
import com.yh.jiran.module.wallet.WalletContract;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function:
 */
public class WalletActivity extends BaseActivity implements WalletContract.View {

    @Override
    protected int setContent() {
        return 0;
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
