package com.yh.jiran.module.user.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yh.core.app.BaseFragment;
import com.yh.jiran.R;
import com.yh.jiran.module.star.view.StarActivity;
import com.yh.jiran.module.user.model.entity.UserStar;
import com.yh.jiran.module.user.view.adapter.UserStarsAdapter;
import com.yh.jiran.utils.Consts;
import com.yh.jiran.utils.RouterMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 闫昊
 * @date: 2018/8/11
 * @function: 个人主页星球列表
 */
public class UserStarsFragment extends BaseFragment {

    public static final String SELF_OPEN_PUSH = "开启推送";
    public static final String SELF_PUSH_DONE = "已开启推送";
    public static final String OTHER_JOIN = "加入星球";
    public static final String OTHER_JOIN_DONE = "已加入";

    private boolean mOtherView;
    private List<UserStar> mHostList = new ArrayList<>();
    private List<UserStar> mJoinList = new ArrayList<>();
    private UserStarsAdapter mHostAdapter;
    private UserStarsAdapter mJoinAdapter;

    @BindView(R.id.tv_host)
    AppCompatTextView tvHost;
    @BindView(R.id.recycler_host)
    RecyclerView recyclerHost;
    @BindView(R.id.tv_join)
    AppCompatTextView tvJoin;
    @BindView(R.id.recycler_join)
    RecyclerView recyclerJoin;

    @Override
    protected Object setContent() {
        return R.layout.fragment_user_stars_layout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_user_stars_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView(savedInstanceState, mRootView);
        return mRootView;
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        tvHost.setText(mOtherView ? "Ta主理的星球" : "我主理的星球");
        tvJoin.setText(mOtherView ? "Ta加入的星球" : "我加入的星球");

        LinearLayoutManager hostManager = new LinearLayoutManager(getContext());
        hostManager.setOrientation(LinearLayoutManager.VERTICAL);
        mHostAdapter = new UserStarsAdapter(mHostList);
        recyclerHost.setLayoutManager(hostManager);
        recyclerHost.setAdapter(mHostAdapter);
        mHostAdapter.bindToRecyclerView(recyclerHost);

        LinearLayoutManager joinManager = new LinearLayoutManager(getContext());
        joinManager.setOrientation(LinearLayoutManager.VERTICAL);
        mJoinAdapter = new UserStarsAdapter(mJoinList);
        recyclerJoin.setLayoutManager(joinManager);
        recyclerJoin.setAdapter(mJoinAdapter);
        mJoinAdapter.bindToRecyclerView(recyclerJoin);

        mHostAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                toStarHome(mHostList.get(position).getStarId());
            }
        });

        mHostAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.iv_star:
                    toStarHome(mHostList.get(position).getStarId());
                    break;
                case R.id.btn_push:
                    AppCompatButton btnPush = (AppCompatButton) mHostAdapter.getViewByPosition(position, R.id.btn_push);
                    if (mOtherView) {
                        if (btnPush.getText().toString().equals(OTHER_JOIN)) {
                            btnPush.setText(OTHER_JOIN_DONE);
                            btnPush.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey2));
                            btnPush.setBackgroundResource(R.drawable.bg_discover_join_done);
                            // TODO: 2018/8/12 发送加入星球消息
                        }
                    } else {
                        if (btnPush.getText().toString().equals(SELF_OPEN_PUSH)) {
                            btnPush.setText(SELF_PUSH_DONE);
                            btnPush.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey2));
                            btnPush.setBackgroundResource(R.drawable.bg_discover_join_done);
                            // TODO: 2018/8/12 发送开启推送消息
                        }
                    }
                    break;
                default:
                    break;
            }
        });

        mJoinAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                toStarHome(mJoinList.get(position).getStarId());
            }
        });

        mJoinAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.iv_star:

                    break;
                case R.id.btn_push:
                    break;
                default:
                    break;
            }
        });
    }

    private void toStarHome(String starId) {
        // TODO: 2018/8/12 跳转至星球，可以用全局工具类统一
        ARouter.getInstance()
                .build(RouterMap.PATH_STAR_HOME_ACTIVITY)
                .withString(StarActivity.STAR_ID, starId)
                .navigation();
    }

    @Override
    protected void initData() {
        super.initData();
        refreshHostList();
        refreshJoinList();
    }

    public boolean isOtherView() {
        return mOtherView;
    }

    public void setOtherView(boolean otherView) {
        this.mOtherView = otherView;
    }

    private void refreshHostList() {
        mHostList.clear();
        for (int i = 0; i < 10; i++) {
            UserStar userStar = new UserStar();
            userStar.setStarName("比特币星星BTC");
            userStar.setStarDesc("工地凤凰网个开放物品佛教网王鹏聚宝盆【我居然敢【偶尔加班v");
            userStar.setStarImage(Consts.BING_PIC1);
            userStar.setInCheck(false);
            userStar.setEliteDynamic(305);
            userStar.setMember(5668);
            userStar.setStatus("主理人");
            userStar.setPush(false);
            mHostList.add(userStar);
        }
        mHostAdapter.notifyDataSetChanged();
    }

    private void refreshJoinList() {
        mJoinList.clear();
        for (int i = 0; i < 10; i++) {
            UserStar userStar = new UserStar();
            userStar.setStarName("比特币星星BTC");
            userStar.setStarDesc("工地凤凰网个开放物品佛教网王鹏聚宝盆【我居然敢【偶尔加班v双方的如果对方给你发个金牛化工的人格");
            userStar.setStarImage(Consts.BING_PIC1);
            userStar.setInCheck(false);
            userStar.setEliteDynamic(305);
            userStar.setMember(5668);
            userStar.setStatus("嘉宾");
            userStar.setPush(false);

            UserStar userStar1 = new UserStar();
            userStar1.setStarName("比特币星星BTC");
            userStar1.setStarDesc("工地凤凰网个开放物品佛教网王鹏聚宝盆【我居然敢【偶尔加班v");
            userStar1.setStarImage(Consts.BING_PIC1);
            userStar1.setInCheck(false);
            userStar1.setEliteDynamic(7856);
            userStar1.setMember(303);
            userStar1.setStatus("成员");
            userStar1.setPush(true);
            mJoinList.add(userStar);
            mJoinList.add(userStar1);
        }
        mJoinAdapter.notifyDataSetChanged();
    }

}
