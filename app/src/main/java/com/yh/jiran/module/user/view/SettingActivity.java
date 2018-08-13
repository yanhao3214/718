package com.yh.jiran.module.user.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yh.jiran.R;
import com.yh.jiran.base.HomeActivity;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.custom.dialog.common.JrDialog;
import com.yh.jiran.utils.RouterMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/8/7
 * @function: 个人主页
 */
@Route(path = RouterMap.PATH_SETTING_ACTIVITY)
public class SettingActivity extends ImmerseActivity {

    private String mPhoneNum = "17372754609";
    private boolean mBindPhone;
    private boolean mBindWechat = true;

    @BindView(R.id.tv_phone)
    AppCompatTextView tvPhone;
    @BindView(R.id.tv_wechat)
    AppCompatTextView tvWechat;

    @Override
    protected int setContent() {
        return R.layout.activity_setting_layout;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    @Override
    protected void initView() {
        super.initView();
        tvPhone.setText(mBindPhone ? mPhoneNum : "未绑定");
        tvWechat.setText(mBindPhone ? "已绑定" : "未绑定");
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick({R.id.iv_back, R.id.tv_phone, R.id.tv_wechat, R.id.tv_grade, R.id.tv_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_phone:
                break;
            case R.id.tv_wechat:
                if (mBindWechat) {
                    new JrDialog(this)
                            .title("微信解绑")
                            .message("确定要解除绑定微信吗？")
                            .negative()
                            .positive((dialog, which) -> {
                                dialog.dismiss();
                                // TODO: 2018/8/13 发送消息:解除绑定微信
                            })
                            .show();
                } else {
                    // TODO: 2018/8/13  跳转微信绑定页面
                }
                break;
            case R.id.tv_grade:
                // TODO: 2018/8/13 跳转至腾讯应用宝，打分
                break;
            case R.id.tv_logout:
                new JrDialog(this)
                        .title("退出登录")
                        .message("确定退出登录吗？")
                        .negative()
                        .positive((dialog, which) -> {
                            ARouter.getInstance()
                                    .build(RouterMap.PATH_LOGIN_ACTIVITY)
                                    .navigation();
                            dialog.dismiss();
                            finish();
                        })
                        .show();
                break;
            default:
                break;
        }
    }
}
