package com.yh.jiran.module.home.presenter;

import com.yh.jiran.module.home.HomeDiscoverContract;
import com.yh.jiran.module.home.model.HomeDiscoverModel;

/**
 * @author: 闫昊
 * @date: 2018/8/3
 * @function: 首页-发现 Presenter
 */
public class HomeDiscoverPresenter implements HomeDiscoverContract.Presenter {
    private HomeDiscoverContract.View mView;
    private HomeDiscoverContract.Model mModel;

    public HomeDiscoverPresenter(HomeDiscoverContract.View view) {
        mView = view;
        mModel = new HomeDiscoverModel();
    }

    @Override
    public void updateStar(int page) {
        mView.refreshStar(mModel.getHotStarData(page));
    }

    @Override
    public void updateDynamic() {
    }

    @Override
    public void sendJoin() {

    }
}
