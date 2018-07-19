package com.yh.myapplication;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;

import com.yh.core.app.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/7/17
 * @function:
 */
public class HomeActivity extends BaseActivity {
    @BindView(R.id.btn_test)
    AppCompatButton btnTest;

    @BindViews({R.id.tv_test, R.id.tv_test_1, R.id.tv_test_2})
    List<AppCompatTextView> mTextView;

    @OnClick(R.id.btn_test)
    public void test() {
        btnTest.setTextColor(ContextCompat.getColor(this, android.R.color.holo_purple));
    }

    @Override
    protected int setContent() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mTextView.get(0).setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        mTextView.get(1).setText("content changed");
        mTextView.get(2).setTextSize(50);
    }
}
