package com.yh.jiran.module.home.view.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yh.jiran.R;
import com.yh.jiran.module.home.model.entity.HomeStar;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/30
 * @function: BaseRecyclerViewAdapterHelper 不适用于GridView？todo 待删除
 */
public class StarGridQuickAdapter extends BaseQuickAdapter<HomeStar, BaseViewHolder> {

    public StarGridQuickAdapter(@Nullable List<HomeStar> data) {
        super(R.layout.item_mine_star_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeStar item) {
        int backRes;
        switch (item.getStatus()) {
            case "主理人":
                backRes = R.drawable.bg_star_status_owner;
                break;
            case "嘉宾":
                backRes = R.drawable.bg_star_status_guest;
                break;
            default:
                backRes = R.drawable.bg_star_status_member;
                break;
        }
        helper.setImageResource(R.id.iv_star, R.drawable.xxx1)
                .setImageResource(R.id.iv_new, R.drawable.ic_new)
                .setText(R.id.tv_status, item.getStatus())
                .setText(R.id.tv_owner, item.getOwner())
                .setText(R.id.tv_title, item.getTitle())
                .setBackgroundRes(R.id.tv_status, backRes);
    }
}
