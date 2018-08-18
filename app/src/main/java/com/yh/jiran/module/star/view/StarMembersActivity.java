package com.yh.jiran.module.star.view;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yh.core.utils.SoftKeyUtil;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.custom.dialog.common.JrDialog;
import com.yh.jiran.custom.dialog.member.MemberOperateDialog;
import com.yh.jiran.custom.dialog.member.callback.MemberOperationType;
import com.yh.jiran.custom.dialog.member.callback.MuteClickListener;
import com.yh.jiran.custom.search.YSearchEdit;
import com.yh.jiran.module.star.model.entity.StarMember;
import com.yh.jiran.module.star.view.adapter.StarMemberAdapter;
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
 * @function: 星球成员页
 */
@Route(path = RouterMap.PATH_STAR_MEMBERS_ACTIVITY)
public class StarMembersActivity extends ImmerseActivity {
    public static final String STAR_MEMBER_VIEW = "mHostView";
    @BindView(R.id.edt_search)
    YSearchEdit edtSearch;
    @BindView(R.id.recycler_host)
    RecyclerView recyclerHost;
    @BindView(R.id.recycler_member)
    RecyclerView recyclerMember;
    @BindView(R.id.layout_nav)
    LinearLayout layoutNav;
    @BindView(R.id.tv_cancel)
    AppCompatTextView tvCancel;
    @BindView(R.id.tv_null)
    AppCompatTextView tvNull;
    @BindView(R.id.layout_origin)
    LinearLayout layoutOrigin;
    @BindView(R.id.recycler_result)
    RecyclerView recyclerResult;
    @BindView(R.id.layout_frame)
    FrameLayout layoutFrame;

    private List<StarMember> mHosts = new ArrayList<>();
    private List<StarMember> mMembers = new ArrayList<>();
    private StarMemberAdapter mHostAdapter;
    private StarMemberAdapter mMemberAdapter;

    /**
     * 观察视角 true == 主理人； false == 非主理人
     */
    @Autowired
    boolean mHostView;

    @Override
    protected int setContent() {
        return R.layout.activity_star_member_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        ARouter.getInstance().inject(this);

        LinearLayoutManager managerHost = new LinearLayoutManager(this);
        managerHost.setOrientation(LinearLayoutManager.VERTICAL);
        mHostAdapter = new StarMemberAdapter(mHosts, mHostView);
        recyclerHost.setLayoutManager(managerHost);
        recyclerHost.setAdapter(mHostAdapter);
        mHostAdapter.bindToRecyclerView(recyclerHost);

        LinearLayoutManager managerMember = new LinearLayoutManager(this);
        managerMember.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerMember.setLayoutManager(managerMember);
        mMemberAdapter = new StarMemberAdapter(mMembers, mHostView);
        recyclerMember.setAdapter(mMemberAdapter);
        mMemberAdapter.bindToRecyclerView(recyclerMember);

        mHosts.clear();
        mHosts.addAll(getHosts());
        mHostAdapter.notifyDataSetChanged();
        mMembers.clear();
        mMembers.addAll(getMembers());
        mMemberAdapter.notifyDataSetChanged();

        mHostAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            StarMember starMember = mHosts.get(position);
            switch (view.getId()) {
                case R.id.iv_image:
                case R.id.tv_name:
                    ARouter.getInstance()
                            .build(RouterMap.PATH_USER_HOME_ACTIVITY)
                            .withString(UserActivity.USER_ID, "345671")
                            .navigation();
                    break;
                case R.id.iv_operate:
                    showRemoveGuest(starMember);
                    break;
                default:
                    break;
            }
        });

        mMemberAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            StarMember starMember = mMembers.get(position);

            switch (view.getId()) {
                case R.id.iv_image:
                case R.id.tv_name:
                    ARouter.getInstance()
                            .build(RouterMap.PATH_USER_HOME_ACTIVITY)
                            .withString(UserActivity.USER_ID, "345671")
                            .navigation();
                    break;
                case R.id.iv_operate:
                    if (starMember.getMuteType() == 0) {
                        showMute(starMember);
                    } else {
                        showRelieveMute(starMember);
                    }

                    break;
                default:
                    break;
            }
        });
    }

    /**
     * 显示移除嘉宾Dialog
     */
    private void showRemoveGuest(StarMember starMember) {
        new MemberOperateDialog(this, MemberOperationType.REMOVE_GUEST)
                .removeGuest(operateDialog -> new JrDialog(StarMembersActivity.this)
                        .title("移除嘉宾")
                        .message("确定要移除" + starMember.getUserName() + "的嘉宾身份吗？")
                        .negative()
                        .positive((dialog, which) -> {
                            // TODO: 2018/8/15 发送消息：移除嘉宾
                            Toast.makeText(StarMembersActivity.this, "移除成功",
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            operateDialog.dismiss();
                        })
                        .show())
                .show();
    }

    /**
     * 显示禁言Dialog
     */
    private void showMute(StarMember starMember) {
        new MemberOperateDialog(StarMembersActivity.this, MemberOperationType.MUTE)
                .mute(new MuteClickListener() {
                    @Override
                    public void temp(Dialog dialogTemp) {
                        new JrDialog(StarMembersActivity.this)
                                .title("禁言")
                                .message("确定要将" + starMember.getUserName() + "禁言3天吗？")
                                .negative()
                                .positive((dialog, which) -> {
                                    // TODO: 2018/8/15 发送消息：禁言3天
                                    Toast.makeText(StarMembersActivity.this, "您已将" +
                                                    starMember.getUserName() + "禁言3天，3天后自动解除",
                                            Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    dialogTemp.dismiss();
                                })
                                .show();
                    }

                    @Override
                    public void forever(Dialog dialogForever) {
                        new JrDialog(StarMembersActivity.this)
                                .title("禁言")
                                .message("确定要将" + starMember.getUserName() + "永久禁言吗？")
                                .negative()
                                .positive((dialog, which) -> {
                                    // TODO: 2018/8/15 发送消息：永久禁言
                                    Toast.makeText(StarMembersActivity.this, "您已将" +
                                            starMember.getUserName() + "永久禁言", Toast.LENGTH_SHORT
                                    ).show();
                                    dialog.dismiss();
                                    dialogForever.dismiss();
                                })
                                .show();
                    }
                })
                .show();
    }

    /**
     * 显示解除禁言Dialog
     */
    private void showRelieveMute(StarMember starMember) {
        new MemberOperateDialog(StarMembersActivity.this, MemberOperationType.RELIEVE_MUTE)
                .relieveMute(dialogRelieve -> new JrDialog(StarMembersActivity.this)
                        .title("禁言")
                        .message("确定要解除" + starMember.getUserName() + "的禁言吗？")
                        .negative()
                        .positive((dialog, which) -> {
                            // TODO: 2018/8/15 发送消息：解除禁言
                            Toast.makeText(StarMembersActivity.this, "您已将" + starMember.getUserName() + "解除禁言", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            dialogRelieve.dismiss();
                        })
                        .show())
                .show();
    }


    @OnClick(R.id.iv_cancel)
    public void onViewClicked() {
        finish();
    }

    private List<StarMember> getHosts() {
        List<StarMember> members = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StarMember member = new StarMember();
            member.setImgUrl(Consts.BING_PIC7);
            member.setUserName("比特大佬");
            member.setStatus("主理人");
            member.setMuteType(0);

            StarMember member1 = new StarMember();
            member1.setImgUrl(Consts.BING_PIC4);
            member1.setUserName("比特小弟");
            member1.setStatus("嘉宾");
            member1.setMuteType(0);

            members.add(member);
            members.add(member1);
        }
        return members;
    }

    private List<StarMember> getMembers() {
        List<StarMember> members = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            StarMember member = new StarMember();
            member.setImgUrl(Consts.BING_PIC5);
            member.setUserName("比特灵晖");
            member.setStatus("成员");
            member.setMuteType(0);

            StarMember member1 = new StarMember();
            member1.setImgUrl(Consts.BING_PIC3);
            member1.setUserName("区块链健强");
            member1.setStatus("成员");
            member1.setMuteType(1);

            StarMember member2 = new StarMember();
            member2.setImgUrl(Consts.BING_PIC3);
            member2.setUserName("以太坊云坤");
            member2.setStatus("成员");
            member2.setMuteType(2);

            members.add(member);
            members.add(member1);
            members.add(member2);
        }
        return members;
    }

    @OnClick({R.id.edt_search, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edt_search:
                layoutNav.setVisibility(View.GONE);
                tvCancel.setVisibility(View.VISIBLE);
                layoutFrame.setForeground(new ColorDrawable(0x7F272A33));
                break;
            case R.id.tv_cancel:
                layoutNav.setVisibility(View.VISIBLE);
                tvCancel.setVisibility(View.GONE);
                layoutFrame.setForeground(null);
                SoftKeyUtil.hideSoftKeyboard(this, edtSearch);
                break;
            default:
                break;
        }
    }
}
