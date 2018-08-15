package com.yh.jiran.module.star.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yh.jiran.R;
import com.yh.jiran.base.ImmerseActivity;
import com.yh.jiran.custom.dialog.common.JrDialog;
import com.yh.jiran.custom.dialog.share.ShareDialog;
import com.yh.jiran.module.star.model.entity.Guest;
import com.yh.jiran.module.star.view.adapter.GuestGridAdapter;
import com.yh.jiran.module.user.view.UserActivity;
import com.yh.jiran.utils.Consts;
import com.yh.jiran.utils.RouterMap;
import com.yh.jiran.utils.image.GlideLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: 闫昊
 * @date: 2018/8/14
 * @function: 星球资料页
 */
@Route(path = RouterMap.PATH_STAR_INFO_ACTIVITY)
public class StarInfoActivity extends ImmerseActivity {

    /**
     * 以什么身份进入星球资料页：
     * 1==主理人；2==成员；3==游客
     */
    public static final String STAR_INFO_STATUS = "status";

    private GuestGridAdapter mAdapter;
    private List<Guest> mGuests = new ArrayList<>();

    @Autowired
    int status;

    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.tv_edit)
    AppCompatTextView tvEdit;
    @BindView(R.id.tv_checking)
    AppCompatTextView tvChecking;
    @BindView(R.id.iv_star)
    AppCompatImageView ivStar;
    @BindView(R.id.tv_name)
    AppCompatTextView tvName;
    @BindView(R.id.tv_time)
    AppCompatTextView tvTime;
    @BindView(R.id.tv_share)
    AppCompatTextView tvShare;
    @BindView(R.id.tv_join)
    AppCompatTextView tvJoin;
    @BindView(R.id.tv_desc)
    AppCompatTextView tvDesc;
    @BindView(R.id.layout_push)
    LinearLayout layoutPush;
    @BindView(R.id.tv_none)
    AppCompatTextView tvNone;
    @BindView(R.id.grid_guest)
    GridView gridGuest;
    @BindView(R.id.tv_member)
    AppCompatTextView tvMember;
    @BindView(R.id.tv_mute)
    AppCompatTextView tvMute;
    @BindView(R.id.layout_mute)
    LinearLayout layoutMute;
    @BindView(R.id.tv_invite)
    AppCompatTextView tvInvite;
    @BindView(R.id.layout_invite)
    LinearLayout layoutInvite;

    @Override
    protected int setContent() {
        return R.layout.activity_star_info_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        ARouter.getInstance().inject(this);

        GlideLoader.loadRound(this, Consts.BING_PIC5, ivStar);

        mAdapter = new GuestGridAdapter(this, mGuests);
        gridGuest.setAdapter(mAdapter);

        mGuests.clear();
        mGuests.addAll(getGuests());
        mAdapter.notifyDataSetChanged();

        gridGuest.setOnItemClickListener((parent, view, position, id) -> ARouter.getInstance()
                .build(RouterMap.PATH_USER_HOME_ACTIVITY)
                .withString(UserActivity.USER_ID, "123456")
                .navigation());
    }

    @OnClick({R.id.iv_back, R.id.tv_edit, R.id.tv_share, R.id.tv_join, R.id.layout_member,
            R.id.layout_mute, R.id.layout_invite, R.id.tv_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_edit:
                ARouter.getInstance()
                        .build(RouterMap.PATH_STAR_EDIT_ACTIVITY)
                        .navigation();
                break;
            case R.id.tv_share:
                new ShareDialog(this, true)
                        .show();
                break;
            case R.id.tv_join:
                tvJoin.setVisibility(View.GONE);
                break;
            case R.id.layout_member:
                ARouter.getInstance()
                        .build(RouterMap.PATH_STAR_MEMBERS_ACTIVITY)
                        .withBoolean(StarMembersActivity.STAR_MEMBER_VIEW, true)
                        .navigation();
                break;
            case R.id.layout_mute:
                ARouter.getInstance()
                        .build(RouterMap.PATH_STAR_MUTES_ACTIVITY)
                        .navigation();
                break;
            case R.id.layout_invite:
                new ShareDialog(this, false)
                        .setTitle("邀请你加入星球")
                        .setText("星球名称+星球介绍")
                        .setImgUrl(Consts.BING_PIC7)
                        .show();
                break;
            case R.id.tv_out:
                String starName = "比特大陆";
                new JrDialog(this)
                        .title("退出星球")
                        .message("确定要退出" + starName + "星球吗？")
                        .negative()
                        .positive((dialog, which) -> {
                            dialog.dismiss();
                            // TODO: 2018/8/14 发送退出星球消息
                        })
                        .show();
                break;
            default:
                break;
        }
    }

    private List<Guest> getGuests() {
        List<Guest> guests = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Guest guest = new Guest();
            guest.setUserName("比特大佬");
            guest.setImgUrl(Consts.BING_PIC6);
            guests.add(guest);
        }
        return guests;
    }
}
