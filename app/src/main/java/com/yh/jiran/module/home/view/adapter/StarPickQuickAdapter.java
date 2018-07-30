package com.yh.jiran.module.home.view.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yh.jiran.R;
import com.yh.jiran.module.home.model.entity.HomeStar;
import com.yh.jiran.module.test.TestContract;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/30
 * @function:
 */
public class StarPickQuickAdapter extends BaseQuickAdapter<HomeStar, BaseViewHolder> {

    public StarPickQuickAdapter(@Nullable List<HomeStar> data) {
        super(R.layout.item_star_pick_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeStar item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_desc, item.getOwner())
                .setImageResource(R.id.iv_star, R.drawable.ic_user_male);
    }
}
