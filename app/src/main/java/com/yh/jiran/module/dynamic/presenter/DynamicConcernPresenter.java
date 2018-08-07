package com.yh.jiran.module.dynamic.presenter;

import com.yh.jiran.module.dynamic.DynamicConcernContract;
import com.yh.jiran.module.dynamic.model.DynamicConcernModel;

/**
 * @author: 闫昊
 * @date: 2018/8/7
 * @function: 动态-关注 Presenter
 */
public class DynamicConcernPresenter implements DynamicConcernContract.Presenter {
    private DynamicConcernContract.View mView;
    private DynamicConcernContract.Model mModel;

    public DynamicConcernPresenter(DynamicConcernContract.View view) {
        mView = view;
        mModel = new DynamicConcernModel();
    }

    @Override
    public void present() {
        updateDynamic(1);
    }

    @Override
    public void updateDynamic(int page) {
        mView.refreshUi(mModel.getDynamicData(page));
    }

    @Override
    public void sendJoin() {

    }
}
