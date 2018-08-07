package com.yh.jiran.module.login.presenter;

import com.yh.jiran.module.home.model.entity.HotStar;
import com.yh.jiran.module.login.LoginContract;
import com.yh.jiran.module.login.model.RecModel;

import java.util.List;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @author: 闫昊
 * @date: 2018/8/6
 * @function:
 */
public class RecPresenter implements LoginContract.RecPresenter {

    private LoginContract.RecView mView;
    private LoginContract.RecModel mModel;

    public RecPresenter(LoginContract.RecView view) {
        mView = view;
        mModel = new RecModel();
    }

    @Override
    public void present(int page) {
        mView.refreshRecs(mModel.getRecStar(page));
    }
}
