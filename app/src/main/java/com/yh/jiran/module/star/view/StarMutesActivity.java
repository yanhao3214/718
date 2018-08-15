package com.yh.jiran.module.star.view;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.custom.dialog.common.JrDialog;
import com.yh.jiran.module.star.model.entity.StarMember;
import com.yh.jiran.module.star.view.adapter.StarMemberAdapter;
import com.yh.jiran.module.star.view.adapter.StarMutesAdapter;
import com.yh.jiran.module.user.view.UserActivity;
import com.yh.jiran.utils.Consts;
import com.yh.jiran.utils.RouterMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/8/15
 * @function: 星球资料-禁言名单
 */
@Route(path = RouterMap.PATH_STAR_MUTES_ACTIVITY)
public class StarMutesActivity extends ImmerseActivity {

    private List<StarMember> mDatas = new ArrayList<>();
    private StarMutesAdapter mAdapter;

    @BindView(R.id.tv_none)
    AppCompatTextView tvNone;
    @BindView(R.id.recycler_mute)
    RecyclerView recyclerMute;

    @Override
    protected int setContent() {
        return R.layout.activity_star_mute_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter = new StarMutesAdapter(mDatas);
        recyclerMute.setLayoutManager(manager);
        recyclerMute.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(recyclerMute);

        mDatas.clear();
        mDatas.addAll(getMutes());
        tvNone.setVisibility(mDatas.size() > 0 ? View.GONE : View.VISIBLE);
        recyclerMute.setVisibility(mDatas.size() > 0 ? View.VISIBLE : View.GONE);
        mAdapter.notifyDataSetChanged();

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            StarMember starMember = mDatas.get(position);
            switch (view.getId()) {
                case R.id.iv_image:
                case R.id.tv_name:
                    ARouter.getInstance()
                            .build(RouterMap.PATH_USER_HOME_ACTIVITY)
                            .withString(UserActivity.USER_ID, "345688")
                            .navigation();
                    break;
                case R.id.tv_operate:
                    new JrDialog(StarMutesActivity.this)
                            .title("解除禁言")
                            .message("确定要解除" + starMember.getUserName() + "的禁言吗？")
                            .negative()
                            .positive((dialog, which) -> {
                                // TODO: 2018/8/15 发送消息：移除嘉宾
                                Toast.makeText(StarMutesActivity.this, "解禁成功",
                                        Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            })
                            .show();

                    // TODO: 2018/8/15 发送消息：解除禁言
                    mDatas.remove(position);
                    mAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }

        });
    }

    @OnClick(R.id.iv_cancel)
    public void onViewClicked() {
        finish();
    }

    private List<StarMember> getMutes() {
        List<StarMember> members = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            StarMember member = new StarMember();
            member.setImgUrl(Consts.BING_PIC5);
            member.setUserName("比特灵晖");
            member.setStatus("成员");
            member.setMuteType(1);

            StarMember member1 = new StarMember();
            member1.setImgUrl(Consts.BING_PIC3);
            member1.setUserName("区块链健强");
            member1.setStatus("成员");
            member1.setMuteType(2);

            StarMember member2 = new StarMember();
            member2.setImgUrl(Consts.BING_PIC3);
            member2.setUserName("以太坊云坤");
            member2.setStatus("成员");
            member2.setMuteType(1);

            members.add(member);
            members.add(member1);
            members.add(member2);
        }
        return members;
    }
}
