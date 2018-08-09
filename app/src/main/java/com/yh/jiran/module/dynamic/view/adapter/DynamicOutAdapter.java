package com.yh.jiran.module.dynamic.view.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.yh.core.utils.NumberUtil;
import com.yh.jiran.R;
import com.yh.jiran.custom.text.AllTextView;
import com.yh.jiran.custom.text.FilterClickMovementMethod;
import com.yh.jiran.custom.text.InnerURLSpan;
import com.yh.jiran.custom.text.ShowAllSpan;
import com.yh.jiran.custom.text.URLSpanNoUnderLine;
import com.yh.jiran.module.dynamic.DynamicConcernContract;
import com.yh.jiran.module.dynamic.model.entity.DynamicOut;
import com.yh.jiran.module.dynamic.view.DynamicDetailActivity;
import com.yh.jiran.utils.Paths;
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void convert(BaseViewHolder helper, DynamicOut item) {
        switch (helper.getItemViewType()) {
            case 1:
                int like = item.getLike();
                int comment = item.getComment();
                helper.setText(R.id.tv_name, item.getStarName())
                        .setText(R.id.tv_time, item.getPublishTime())
//                        .setText(R.id.tv_text, item.getText())
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
                        .addOnClickListener(R.id.layout_link)
                        .addOnClickListener(R.id.iv_author)
                        .addOnClickListener(R.id.tv_author)
                        .addOnClickListener(R.id.tv_like)
                        .addOnClickListener(R.id.tv_comment)
                        .addOnClickListener(R.id.tv_share);

                /**
                 * 加载头像
                 */
                GlideLoader.loadRound(mContext, item.getImgStarUrl(), helper.getView(R.id.iv_star), 8);
                GlideLoader.loadCircle(mContext, item.getImgAuthorUrl(), helper.getView(R.id.iv_author));

                /**
                 * 设置正文：1.“全文”跳转；2.超链接 跳转
                 */
                AllTextView tvText = helper.getView(R.id.tv_text);
                InnerURLSpan innerURLSpan = new InnerURLSpan(mContext, "优酷视频", "https://www.youku.com/");
                SpannableString ss = new SpannableString(item.getText());
                ss.setSpan(innerURLSpan, 3, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvText.setText(ss);
                tvText.setOnTouchListener(FilterClickMovementMethod.getInstance());
                tvText.setMaxShowLines(6);
                tvText.setMyText(tvText.getText());
                tvText.setOnAllSpanClickListener(widget -> ARouter.getInstance()
                        .build(Paths.PATH_DYNAMIC_DETAIL_ACTIVITY)
                        .withString(DynamicDetailActivity.DYNAMIC_ID, item.getDynamicId())
                        .navigation());


//                /**
//                 * 设置正文：2.超链接 跳转
//                 */
//                AppCompatTextView tvUrl = helper.getView(R.id.tv_url);
//                InnerURLSpan innerURLSpan = new InnerURLSpan(mContext, "优酷视频", "https://www.youku.com/");
//                SpannableString ss = new SpannableString(item.getText());
//                ss.setSpan(innerURLSpan, 3, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                tvUrl.setText(ss);
////                tvUrl.setMovementMethod(LinkMovementMethod.getInstance());
//                tvUrl.setOnTouchListener(FilterClickMovementMethod.getInstance());

                /**
                 * 设置九宫格图片
                 */
                NineGridImageView<String> nineGridImageView = helper.getView(R.id.iv_nine);
                if (null != item.getImgUrl()) {
                    nineGridImageView.setAdapter(new NineImageAdapter());
                    nineGridImageView.setImagesData(item.getImgUrl());
                } else {
                    nineGridImageView.setVisibility(View.GONE);
                }

                /**
                 * 设置链接布局的内容
                 */
                boolean hasLink = !TextUtils.isEmpty(item.getLinkUrl());
                LinearLayout layoutLink = helper.getView(R.id.layout_link);
                if (hasLink) {
                    GlideLoader.load(mContext, item.getLinkImage(), helper.getView(R.id.iv_link));
                    helper.setText(R.id.tv_link, item.getLinkContent());
                } else {
                    layoutLink.setVisibility(View.GONE);
                }

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
