package com.yh.jiran.mvp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yh.core.app.BaseActivity;
import com.yh.jiran.R;
import com.yh.jiran.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/7/18
 * @function: 主界面
 */
public class MainActivity extends BaseActivity {

    @OnClick(R.id.btn_test)
    public void jump() {
        startActivity(new Intent(this, CourierActivity.class));
    }

    @Override
    protected int setContent() {
        return R.layout.activity_main_layout;
    }
}
