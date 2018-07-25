package com.yh.jiran.module.test.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yh.core.app.BaseActivity;
import com.yh.jiran.R;
import com.yh.jiran.base.contract.IPresenter;
import com.yh.jiran.module.test.TestContract;
import com.yh.jiran.module.test.presenter.TestPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function:
 */
public class TestActivity extends BaseActivity implements TestContract.View {
    private TestContract.Presenter mPresenter;

    @BindView(R.id.tv_test)
    TextView tvTest;
    @BindView(R.id.edt_test)
    EditText edtTest;

    @OnClick(R.id.btn_test)
    public void postTest() {
        mPresenter.showData();
    }

    @Override
    protected int setContent() {
        return R.layout.activity_test_layout;
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new TestPresenter(this);
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

    @Override
    public String getInput() {
        return edtTest.getText().toString().trim();
    }

    @Override
    public void update(String str) {
        tvTest.setText(str);
    }
}
