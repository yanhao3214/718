package com.yh.jiran.module.dynamic.view.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.yh.jiran.R;
import com.yh.jiran.module.dynamic.model.entity.DynamicOut;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/8/7
 * @function: 星球外动态列表适配器
 */
public class DynamicOutAdapter extends BaseQuickAdapter<DynamicOut, BaseViewHolder> {

    public DynamicOutAdapter(@Nullable List<DynamicOut> data) {
        super(data);
        setMultiTypeDelegate(new MultiTypeDelegate<DynamicOut>() {
            @Override
            protected int getItemType(DynamicOut dynamicOut) {
                return dynamicOut.type;
            }
        });

        getMultiTypeDelegate()
                .registerItemType(DynamicOut.TEXT, R.layout.item_dynamic_text_layout)
                .registerItemType(DynamicOut.SINGLE_PIC, R.layout.item_dynamic_single_pic_layout)
                .registerItemType(DynamicOut.MULTI_PIC, R.layout.item_dynamic_multi_pic_layout)
                .registerItemType(DynamicOut.LINK, R.layout.item_dynamic_link_layout)
                .registerItemType(DynamicOut.LINK_RAW, R.layout.item_dynamic_link_raw_layout)
                .registerItemType(DynamicOut.LIKE_AND_COLLECT, R.layout.item_dynamic_like_layout)
                .registerItemType(DynamicOut.FORWARD_DELETE, R.layout.item_dynamic_multi_forward_delete_layout)
                .registerItemType(DynamicOut.FORWARD_MULTI_PIC, R.layout.item_dynamic_forward_multi_layout)
                .registerItemType(DynamicOut.FORWARD_LINK, R.layout.item_dynamic_forward_link_layout)
                .registerItemType(DynamicOut.FORWARD_SINGLE_PIC, R.layout.item_dynamic_forward_single_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, DynamicOut item) {

    }
}
