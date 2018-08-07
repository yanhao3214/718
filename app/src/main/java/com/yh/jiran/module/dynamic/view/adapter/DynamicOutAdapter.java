package com.yh.jiran.module.dynamic.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.yh.core.utils.NumberUtil;
import com.yh.jiran.R;
import com.yh.jiran.module.dynamic.DynamicConcernContract;
import com.yh.jiran.module.dynamic.model.entity.DynamicOut;
import com.yh.jiran.utils.image.GlideLoader;

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
                return Integer.parseInt(dynamicOut.type);
            }
        });

        getMultiTypeDelegate()
                .registerItemType(1, R.layout.item_dynamic_connate_layout)
                .registerItemType(2, R.layout.item_dynamic_forward_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, DynamicOut item) {
        switch (helper.getItemViewType()) {
            case 1:
                int like = item.getLike();
                int comment = item.getComment();
                helper.setText(R.id.tv_name, item.getStarName())
                        .setText(R.id.tv_time, item.getPublishTime())
                        .setText(R.id.tv_text, item.getText())
                        .setText(R.id.tv_author, item.getAuthorName())
                        .setVisible(R.id.btn_join, !item.isIn())
                        .setVisible(R.id.btn_more, item.isIn())
                        .setText(R.id.tv_like, like == 0 ? "点赞" : NumberUtil.parseToK(like))
                        .setText(R.id.tv_comment, comment == 0 ? "评论" : NumberUtil.parseToK(comment))
                        .addOnClickListener(R.id.tv_trigger)
                        .addOnClickListener(R.id.iv_star)
                        .addOnClickListener(R.id.btn_join)
                        .addOnClickListener(R.id.btn_more)
                        .addOnClickListener(R.id.tv_text)
                        .addOnClickListener(R.id.iv_author)
                        .addOnClickListener(R.id.tv_author)
                        .addOnClickListener(R.id.tv_like)
                        .addOnClickListener(R.id.tv_comment)
                        .addOnClickListener(R.id.tv_share);

                GlideLoader.loadRound(mContext, item.getImgStarUrl(), helper.getView(R.id.iv_star), 8);
                GlideLoader.loadCircle(mContext, item.getImgAuthorUrl(), helper.getView(R.id.iv_author));

                /**
                 * 设置九宫格图片
                 */
                NineGridImageView<String> nineGridImageView = helper.getView(R.id.iv_nine);
                nineGridImageView.setVisibility(item.getImgUrl().size() == 0 ? View.GONE : View.VISIBLE);
                nineGridImageView.setAdapter(new NineImageAdapter());
                nineGridImageView.setImagesData(item.getImgUrl());

                /**
                 * 设置点赞、收藏
                 */
                int action = item.getTriggerAction();
                RelativeLayout layout = helper.getView(R.id.layout_action);
                layout.setVisibility(action <= DynamicOut.TRIGGER_ACTION_COLLECT ? View.VISIBLE : View.GONE);
                if (action <= DynamicOut.TRIGGER_ACTION_COLLECT) {
                    helper.setText(R.id.tv_trigger, item.getTriggerName())
                            .setText(R.id.tv_action, action == DynamicOut.TRIGGER_ACTION_LIKE ? "点赞了" : "收藏了");
                }

                break;
            case 2:
                break;
            default:
                break;
        }
    }
}
