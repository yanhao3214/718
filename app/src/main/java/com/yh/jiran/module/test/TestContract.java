package com.yh.jiran.module.test;

import com.yh.jiran.base.contract.IModel;
import com.yh.jiran.base.contract.IPresenter;
import com.yh.jiran.base.contract.IView;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function:
 */
public interface TestContract {

    interface Model extends IModel<String> {
        Observable<String> getData(RequestBody body);
    }

    interface View extends IView {
        String getInput();

        void update(String str);
    }

    interface Presenter extends IPresenter {
        void showData();
    }
}
