package com.yh.jiran.module.home.presenter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yh.jiran.module.home.HomeMineContract;
import com.yh.jiran.module.home.model.HomeMineModel;
import com.yh.jiran.module.home.model.entity.MineStar;
import com.yh.jiran.utils.Paths;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function:
 */
public class HomeMinePresenter implements HomeMineContract.Presenter {
    private HomeMineContract.View mView;
    private HomeMineContract.Model mModel;

    public HomeMinePresenter(HomeMineContract.View mView) {
        this.mView = mView;
        mModel = new HomeMineModel();
    }

    @Override
    public List<MineStar> upDateStars() {
        return mModel.getNetData();
    }

    @Override
    public void jumpToSearch() {
        ARouter.getInstance().build(Paths.PATH_SEARCH_ACTIVITY).navigation();
    }
}
