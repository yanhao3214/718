package com.yh.jiran.module.home.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yh.core.utils.NumberUtil;
import com.yh.jiran.R;
import com.yh.jiran.module.home.model.entity.HotStar;
import com.yh.jiran.utils.Consts;
import com.yh.ui.img.GlideRoundTransform;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/8/2
 * @function: 首页-热门星球：RecyclerView适配器
 */
public class DiscoverStarQuickAdapter extends BaseQuickAdapter<HotStar, BaseViewHolder> {
    private RequestOptions mOptions;

    public DiscoverStarQuickAdapter(@Nullable List<HotStar> data) {
        super(R.layout.item_discover_star_item, data);
        mOptions = new RequestOptions().centerCrop().transform(new GlideRoundTransform(mContext));
    }

    @Override
    protected void convert(BaseViewHolder helper, HotStar item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_member, "成员 ".concat(NumberUtil.parseToK(item.getMember())))
                .addOnClickListener(R.id.btn_join);
        if (item.isIn()) {
            helper.setText(R.id.btn_join, "已加入")
                    .setTextColor(R.id.btn_join, ContextCompat.getColor(mContext, R.color.textDark3))
                    .setBackgroundRes(R.id.btn_join, R.drawable.bg_discover_join_done);
        } else {
            helper.setText(R.id.btn_join, "加入")
                    .setTextColor(R.id.btn_join, ContextCompat.getColor(mContext, R.color.text_blue))
                    .setBackgroundRes(R.id.btn_join, R.drawable.bg_discover_join_normal);
        }
        Glide.with(mContext)
                .load(item.isIn() ? R.drawable.xxx1 : Consts.BING_PIC)
                .apply(mOptions)
                .into((AppCompatImageView) helper.getView(R.id.iv_image));
    }
}
