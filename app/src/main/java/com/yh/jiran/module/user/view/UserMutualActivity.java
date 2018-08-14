package com.yh.jiran.module.user.view;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.module.user.model.entity.UserMutual;
import com.yh.jiran.module.user.view.adapter.UserMutualAdapter;
import com.yh.jiran.module.user.view.adapter.UserStarsAdapter;
import com.yh.jiran.utils.Consts;
import com.yh.jiran.utils.RouterMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/8/13
 * @function: 粉丝或者关注列表
 */
@Route(path = RouterMap.PATH_USER_MUTUAL_ACTIVITY)
public class UserMutualActivity extends ImmerseActivity {
    public static final String USER_ID = "mUserId";
    public static final String USER_TYPE = "fans_or_concerns";
    public static final String USER_VIEW = "other_view";

    private List<UserMutual> mDatas = new ArrayList<>();
    private UserMutualAdapter mAdapter;

    @Autowired
    String mUserId;
    @Autowired(name = USER_TYPE)
    String mTitle;
    @Autowired(name = USER_VIEW)
    boolean mOtherView;

    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_none)
    AppCompatTextView tvNone;

    @Override
    protected int setContent() {
        return R.layout.activity_fans_or_concern_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        ARouter.getInstance().inject(this);

        tvTitle.setText(mTitle);

        mAdapter = new UserMutualAdapter(mDatas, mOtherView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        refreshList();

        mAdapter.setOnItemClickListener((adapter, view, position) -> ARouter.getInstance()
                .build(RouterMap.PATH_USER_HOME_ACTIVITY)
                .withString(UserActivity.USER_ID, "10007")
                .withBoolean(UserActivity.USER_OTHER_VIEW, true)
                .withBoolean(UserActivity.USER_HAS_COLLECT, false)
                .navigation());

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            UserMutual userMutual = mDatas.get(position);
            boolean beConcerned = userMutual.isBeConcerned();
            boolean concernMe = userMutual.isConcernMe();
            AppCompatTextView tvOperate = (AppCompatTextView) adapter.getViewByPosition(position, R.id.tv_operate);
            AppCompatTextView tvDone = (AppCompatTextView) adapter.getViewByPosition(position, R.id.tv_done);
            AppCompatTextView tvMutual = (AppCompatTextView) adapter.getViewByPosition(position, R.id.tv_mutual);
            switch (view.getId()) {
                case R.id.tv_operate:
                    tvOperate.setVisibility(View.INVISIBLE);
                    if (concernMe) {
                        tvMutual.setVisibility(View.VISIBLE);
                    } else {
                        tvDone.setVisibility(View.VISIBLE);
                    }
                    Toast.makeText(this, "关注用户", Toast.LENGTH_SHORT).show();
                    // TODO: 2018/8/13 发送消息：关注用户
                    break;
                case R.id.tv_done:
                    tvDone.setVisibility(View.INVISIBLE);
                    tvOperate.setVisibility(View.VISIBLE);
                    Toast.makeText(this, "取消关注", Toast.LENGTH_SHORT).show();
                    // TODO: 2018/8/13 发送消息：取消关注
                    break;
                case R.id.tv_mutual:
                    tvMutual.setVisibility(View.INVISIBLE);
                    tvOperate.setVisibility(View.VISIBLE);
                    Toast.makeText(this, "取消关注", Toast.LENGTH_SHORT).show();
                    // TODO: 2018/8/13 发送消息：取消关注
                    break;
                default:
                    break;
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void refreshList() {
        mDatas.clear();
        for (int i = 0; i < 5; i++) {
            UserMutual userMutual = new UserMutual();
            userMutual.setBeConcerned(true);
            userMutual.setDesc("这个人很帅，什么都没有留下");
            userMutual.setConcernMe(true);
            userMutual.setImgUrl(Consts.BING_PIC5);
            userMutual.setUserId("10004");
            userMutual.setUserName("区块链急先锋");

            UserMutual userMutual1 = new UserMutual();
            userMutual1.setBeConcerned(true);
            userMutual1.setDesc("啦啦啦给哦豁到is多好的女为大声道");
            userMutual1.setConcernMe(false);
            userMutual1.setImgUrl(Consts.BING_PIC4);
            userMutual1.setUserId("10005");
            userMutual1.setUserName("创世区块");

            UserMutual userMutual2 = new UserMutual();
            userMutual2.setBeConcerned(false);
            userMutual2.setDesc("怄气文化的期望能陪女评委女全文收到钱未付");
            userMutual2.setConcernMe(false);
            userMutual2.setImgUrl(Consts.BING_PIC3);
            userMutual2.setUserId("10006");
            userMutual2.setUserName("创世区块");

            mDatas.add(userMutual);
            mDatas.add(userMutual1);
            mDatas.add(userMutual2);
        }

        recyclerView.setVisibility(mDatas.size() > 0 ? View.VISIBLE : View.GONE);
        tvNone.setVisibility(mDatas.size() > 0 ? View.GONE : View.VISIBLE);

        tvNone.setText("暂无" + mTitle);
        mAdapter.notifyDataSetChanged();

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

}
