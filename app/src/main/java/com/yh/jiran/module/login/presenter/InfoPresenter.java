package com.yh.jiran.module.login.presenter;

import com.yh.jiran.module.login.LoginContract;
import com.yh.jiran.module.login.model.InfoModel;

/**
 * @author: 闫昊
 * @date: 2018/8/4
 * @function:
 */
public class InfoPresenter implements LoginContract.InfoPresenter {
    private LoginContract.InfoView mView;
    private LoginContract.InfoModel mModel;

    public InfoPresenter(LoginContract.InfoView view) {
        mView = view;
        mModel = new InfoModel();
    }

    @Override
    public boolean verifyRepeat(String name) {
        return mModel.getRepeat(name);
    }

    @Override
    public void present() {
        mView.setDefaultName(mModel.getDefaultName());
    }
}
