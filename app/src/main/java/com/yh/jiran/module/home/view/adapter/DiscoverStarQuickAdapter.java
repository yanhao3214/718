package com.yh.jiran.module.home.view.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yh.core.utils.NumberUtil;
import com.yh.jiran.R;
import com.yh.jiran.module.home.model.entity.HotStar;
import com.yh.ui.img.GlideRoundTransform;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/8/2
 * @function:
 */
public class DiscoverStarQuickAdapter extends BaseQuickAdapter<HotStar, BaseViewHolder> {
    private RequestOptions mOptions;

    public DiscoverStarQuickAdapter(@Nullable List<HotStar> data) {
        super(R.layout.item_discover_star_item, data);
        mOptions = new RequestOptions().centerCrop().transform(new GlideRoundTransform(mContext, 2));
    }

    @Override
    protected void convert(BaseViewHolder helper, HotStar item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_member, "成员 ".concat(NumberUtil.parseToK(item.getMember())))
                .addOnClickListener(R.id.btn_join);
        Glide.with(mContext)
                .load(R.drawable.xxx1)
                .apply(mOptions)
                .into((AppCompatImageView) helper.getView(R.id.iv_image));
    }
}
