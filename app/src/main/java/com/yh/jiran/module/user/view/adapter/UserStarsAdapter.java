package com.yh.jiran.module.user.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yh.core.utils.NumberUtil;
import com.yh.jiran.R;
import com.yh.jiran.module.user.model.entity.UserStar;
import com.yh.jiran.utils.image.GlideLoader;
import com.yh.ui.img.GlideRoundTransform;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/8/11
 * @function: 个人主页星球列表适配器
 */
public class UserStarsAdapter extends BaseQuickAdapter<UserStar, BaseViewHolder> {

    private boolean mOtherView;
    private RequestOptions mOptions;

    public UserStarsAdapter(@Nullable List<UserStar> data) {
        super(R.layout.item_user_star_layout, data);
        mOptions = new RequestOptions().centerCrop().transform(new GlideRoundTransform(mContext));
        mOtherView = false;
    }

    public UserStarsAdapter(@Nullable List<UserStar> data, boolean otherView) {
        this(data);
        mOtherView = otherView;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserStar item) {
        helper.setText(R.id.tv_name, item.getStarName())
                .setText(R.id.tv_desc, item.getStarDesc())
                .setText(R.id.tv_elite, "精华" + NumberUtil.parseToK(item.getEliteDynamic()))
                .setText(R.id.tv_member, "成员" + NumberUtil.parseToK(item.getMember()))
                .setText(R.id.btn_push, mOtherView ? (item.isIn() ? "已加入" : "加入星球")
                        : (item.isPush() ? "已开启推送" : "开启推送"))
                .setText(R.id.tv_status, item.getStatus())
                .setVisible(R.id.tv_checking, item.isInCheck())
                .addOnClickListener(R.id.iv_star)
                .addOnClickListener(R.id.btn_push);

        GlideLoader.loadRound(mContext, item.getStarImage(), helper.getView(R.id.iv_star), 2);
        AppCompatTextView tvStatus = helper.getView(R.id.tv_status);
        switch (item.getStatus()) {
            case "主理人":
                tvStatus.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bg_star_status_owner));
                break;
            case "嘉宾":
                tvStatus.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bg_star_status_guest));
                break;
            default:
                tvStatus.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bg_star_status_member));
                break;
        }

        if (item.isPush()) {
            helper.setText(R.id.btn_push, "已开启推送")
                    .setTextColor(R.id.btn_push, ContextCompat.getColor(mContext, R.color.textDark3))
                    .setBackgroundRes(R.id.btn_push, R.drawable.bg_discover_join_done);
        } else {
            helper.setText(R.id.btn_push, "开启推送")
                    .setTextColor(R.id.btn_push, ContextCompat.getColor(mContext, R.color.text_blue))
                    .setBackgroundRes(R.id.btn_push, R.drawable.bg_discover_join_normal);
        }
    }
}
