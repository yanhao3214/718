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
public class StarMutesAdapter extends BaseQuickAdapter<StarMember, BaseViewHolder> {
    private List<StarMember> mDatas;

    public StarMutesAdapter(@Nullable List<StarMember> data) {
        super(R.layout.item_star_mute_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StarMember item) {

        AppCompatTextView tvMute = helper.getView(R.id.tv_mute);

        helper.setText(R.id.tv_name, item.getUserName())
                .addOnClickListener(R.id.iv_image)
                .addOnClickListener(R.id.tv_name)
                .addOnClickListener(R.id.tv_operate);

        GlideLoader.load(mContext, item.getImgUrl(), helper.getView(R.id.iv_image));
        if (item.getMuteType() == 1) {
            tvMute.setText("禁言剩余3天");
        } else {
            tvMute.setText("永久禁言");
        }
    }
}
