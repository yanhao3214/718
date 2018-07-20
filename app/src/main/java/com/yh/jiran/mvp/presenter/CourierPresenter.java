package com.yh.jiran.mvp.presenter;

import android.util.Log;

import com.yh.jiran.mvp.contact.CourierContract;
import com.yh.jiran.mvp.model.CourierModel;

/**
 * @author: 闫昊
 * @date: 2018/7/20
 * @function:
 */
public class CourierPresenter implements CourierContract.Presenter {
    private CourierContract.View mView;
    private CourierContract.Model mModel;

    public CourierPresenter(CourierContract.View view) {
        this.mView = view;
        mModel = new CourierModel();
    }

    @Override
    public void query() {
        String com = mView.getCom();
        String num = mView.getNumber();
        mView.updateUi(mModel.getDatas(com, num));
    }
}
