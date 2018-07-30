package com.yh.jiran.module.home.presenter;

import com.yh.jiran.module.home.HomeMineContract;
import com.yh.jiran.module.home.model.PickStarModel;
import com.yh.jiran.module.home.model.entity.HomeStar;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/27
 * @function:
 */
public class PickStarPresenter implements HomeMineContract.PickStarPresenter {

    private HomeMineContract.PickStarView mView;
    private HomeMineContract.PickStarModel mModel;

    public PickStarPresenter(HomeMineContract.PickStarView view) {
        mView = view;
        mModel = new PickStarModel();
    }


    @Override
    public List<HomeStar> getMyStar() {
        return mModel.getMyStar();
    }

    @Override
    public List<HomeStar> getRecentStar() {
        return mModel.getRecentStar();
    }

    @Override
    public List<HomeStar> getHotStar() {
        return mModel.getHotStar();
    }
}
