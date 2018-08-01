package com.yh.jiran.custom.search;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yh.jiran.R;
import com.yh.jiran.module.home.model.entity.SearchHistory;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/30
 * @function: 星球搜索历史记录适配器
 */
public class SearchHistoryAdapter extends BaseQuickAdapter<SearchHistory, BaseViewHolder> {

    public SearchHistoryAdapter(@Nullable List<SearchHistory> data) {
        super(R.layout.item_star_history_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchHistory item) {
        helper.setText(R.id.tv_history, item.getContent())
                .addOnClickListener(R.id.iv_delete);
    }
}
