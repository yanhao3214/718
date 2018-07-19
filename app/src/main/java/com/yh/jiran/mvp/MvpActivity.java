package com.yh.jiran.mvp;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;

import com.yh.core.app.BaseActivity;
import com.yh.jiran.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/7/19
 * @function: MVP架构页
 */
public class MvpActivity extends BaseActivity implements IView {
    private IPresenter mPresenter;

    @BindView(R.id.edt_query)
    AppCompatEditText edtQuery;

    @BindView(R.id.btn_submit)
    AppCompatButton btnSubmit;

    @BindView(R.id.tv_mvp)
    AppCompatTextView tvResult;

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new Presenter(this);
    }

    @OnClick(R.id.btn_submit)
    public void search() {
        mPresenter.search();
    }

    @Override
    protected int setContent() {
        return R.layout.activity_mvp_layout;
    }

    @Override
    public String getInput() {
        return edtQuery.getText().toString();
    }

    @Override
    public void setResult(String result) {
        tvResult.setText(result);
    }
}
