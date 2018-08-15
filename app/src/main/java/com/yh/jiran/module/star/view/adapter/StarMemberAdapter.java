package com.yh.jiran.module.star.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yh.jiran.R;
import com.yh.jiran.module.star.model.entity.StarMember;
import com.yh.jiran.utils.image.GlideLoader;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/8/15
 * @function: 星球成员列表适配器
 */
public class StarMemberAdapter extends BaseQuickAdapter<StarMember, BaseViewHolder> {
    private List<StarMember> mDatas;
    private boolean mHostView;

    public StarMemberAdapter(@Nullable List<StarMember> data) {
        this(data, false);
    }

    public StarMemberAdapter(@Nullable List<StarMember> data, boolean hostView) {
        super(R.layout.item_star_member_layout, data);
        mHostView = hostView;
    }

    @Override
    protected void convert(BaseViewHolder helper, StarMember item) {

        String status = item.getStatus();
        AppCompatTextView tvMute = helper.getView(R.id.tv_mute);
        AppCompatTextView tvStatus = helper.getView(R.id.tv_status);
        AppCompatImageView ivOperate = helper.getView(R.id.iv_operate);

        helper.setText(R.id.tv_name, item.getUserName())
                .addOnClickListener(R.id.iv_image)
                .addOnClickListener(R.id.tv_name)
                .addOnClickListener(R.id.iv_operate);


        GlideLoader.load(mContext, item.getImgUrl(), helper.getView(R.id.iv_image));
        switch (status) {
            case "主理人":
                tvStatus.setText("主理人");
                tvStatus.setTextColor(ContextCompat.getColor(mContext, R.color.text_blue));
                break;
            case "嘉宾":
                tvStatus.setText("嘉宾");
                tvStatus.setTextColor(ContextCompat.getColor(mContext, R.color.color_orange));
                break;
            default:
                tvStatus.setText("");
                break;
        }
        if (mHostView) {
            if ("主理人".equals(status)) {
                ivOperate.setVisibility(View.GONE);
            } else {
                ivOperate.setVisibility(View.VISIBLE);
            }

            tvMute.setVisibility(View.VISIBLE);
            switch (item.getMuteType()) {
                case 1:
                    tvMute.setVisibility(View.VISIBLE);
                    tvMute.setText("禁言剩余3天");
                    break;
                case 2:
                    tvMute.setVisibility(View.VISIBLE);
                    tvMute.setText("永久禁言");
                    break;
                default:
                    tvMute.setVisibility(View.GONE);
                    break;
            }
        } else {
            ivOperate.setVisibility(View.GONE);
            tvMute.setVisibility(View.GONE);
        }
    }
}
