package com.yh.jiran.module.user.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yh.core.app.BaseFragment;
import com.yh.jiran.R;
import com.yh.jiran.custom.dialog.share.ShareDialog;
import com.yh.jiran.utils.Consts;
import com.yh.jiran.utils.RouterMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author: 闫昊
 * @date: 2018/7/25
 * @function: 个人中心
 */
public class UserFragment extends BaseFragment {
    @BindView(R.id.tv_name)
    AppCompatTextView tvName;
    @BindView(R.id.tv_desc)
    AppCompatTextView tvDesc;
    @BindView(R.id.iv_portrait)
    CircleImageView ivPortrait;
    @BindView(R.id.num_star)
    AppCompatTextView numStar;
    @BindView(R.id.num_concern)
    AppCompatTextView numConcern;
    @BindView(R.id.num_fans)
    AppCompatTextView numFans;
    @BindView(R.id.num_collect)
    AppCompatTextView numCollect;

    @Override
    protected Object setContent() {
        return R.layout.fragment_user_layout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_user_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView(savedInstanceState, mRootView);
        return mRootView;
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
    }

    @OnClick({R.id.iv_portrait, R.id.num_star, R.id.num_concern, R.id.num_fans,
            R.id.num_collect, R.id.tv_invite, R.id.tv_help, R.id.tv_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_portrait:
                ARouter.getInstance()
                        .build(RouterMap.PATH_USER_HOME_ACTIVITY)
                        .withString(UserActivity.USER_ID, "10003")
                        .navigation();
                break;
            case R.id.num_star:
                ARouter.getInstance()
                        .build(RouterMap.PATH_USER_HOME_ACTIVITY)
                        .withInt(UserActivity.USER_TAB, 1)
                        .navigation();
                break;
            case R.id.num_concern:
                Toast.makeText(getActivity(), "关注列表", Toast.LENGTH_SHORT).show();
                ARouter.getInstance()
                        .build(RouterMap.PATH_USER_MUTUAL_ACTIVITY)
                        .withBoolean(UserMutualActivity.USER_VIEW, false)
                        .withString(UserMutualActivity.USER_TYPE, "关注")
                        .withString(UserMutualActivity.USER_ID, "10005")
                        .navigation();
                break;
            case R.id.num_fans:
                Toast.makeText(getActivity(), "粉丝列表", Toast.LENGTH_SHORT).show();
                ARouter.getInstance()
                        .build(RouterMap.PATH_USER_MUTUAL_ACTIVITY)
                        .withBoolean(UserMutualActivity.USER_VIEW, true)
                        .withString(UserMutualActivity.USER_TYPE, "粉丝")
                        .withString(UserMutualActivity.USER_ID, "10006")
                        .navigation();
                break;
            case R.id.num_collect:
                ARouter.getInstance()
                        .build(RouterMap.PATH_USER_HOME_ACTIVITY)
                        .withInt(UserActivity.USER_TAB, 2)
                        .navigation();
                break;
            case R.id.tv_invite:
                new ShareDialog(getContext(), false)
                        .setUrl(Consts.BING_PIC)
                        .setTitle("分享标题")
                        .setText("这是分享内容，真精彩哈")
                        .show();
                break;
            case R.id.tv_help:
                ARouter.getInstance()
                        .build(RouterMap.PATH_STAR_HOME_ACTIVITY)
                        .navigation();
                break;
            case R.id.tv_set:
                ARouter.getInstance()
                        .build(RouterMap.PATH_SETTING_ACTIVITY)
                        .navigation();
                break;
            default:
                break;
        }
    }
}
