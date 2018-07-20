package com.yh.jiran.mvp;

import android.text.TextUtils;

/**
 * @author: 闫昊
 * @date: 2018/7/19
 * @function:
 */
public class Presenter implements IPresenter {
    private IView mView;
    private IModel mModel;

    public Presenter(IView view) {
        this.mView = view;
        mModel = new Model();
    }

    @Override
    public void search() {
        String input = mView.getInput();
        if (TextUtils.isEmpty(input)) {
            return;
        }
        int code = input.hashCode();
        String result = mModel.search(code);
        mView.setResult(result);
    }
}
