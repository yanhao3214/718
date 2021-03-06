package com.yh.jiran.module.dynamic.view.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.yh.core.utils.NumberUtil;
import com.yh.core.utils.UrlUtil;
import com.yh.jiran.R;
import com.yh.jiran.custom.text.AllTextView;
import com.yh.jiran.custom.text.FilterClickMovementMethod;
import com.yh.jiran.custom.text.InnerURLSpan;
import com.yh.jiran.module.dynamic.model.entity.DynamicOut;
import com.yh.jiran.module.dynamic.view.DynamicForwardActivity;
import com.yh.jiran.utils.RouterMap;
import com.yh.jiran.utils.image.GlideLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/8/7
 * @function: 星球外动态列表适配器
 */
public class DynamicOutAdapter extends BaseQuickAdapter<DynamicOut, BaseViewHolder> {
    private boolean mSelfCollect = false;

    public DynamicOutAdapter(@Nullable List<DynamicOut> data) {
        super(data);
        mSelfCollect = false;
        setMultiTypeDelegate(new MultiTypeDelegate<DynamicOut>() {
            @Override
            protected int getItemType(DynamicOut dynamicOut) {
                return Integer.parseInt(dynamicOut.type);
            }
        });

        getMultiTypeDelegate()
                .registerItemType(1, R.layout.item_dynamic_out_connate_layout)
                .registerItemType(2, R.layout.item_dynamic_out_forward_layout);
    }


    public DynamicOutAdapter(@Nullable List<DynamicOut> data, boolean selfCollect) {
        this(data);
        mSelfCollect = selfCollect;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void convert(BaseViewHolder helper, DynamicOut item) {
        switch (helper.getItemViewType()) {
            case 1:
                int like1 = item.getLike();
                int comment1 = item.getComment();
                helper.setText(R.id.tv_name, item.getStarName())
                        .setText(R.id.tv_time, item.getPublishTime())
//                        .setText(R.id.tv_text, item.getText())
                        .setText(R.id.tv_author, item.getAuthorName())
                        .setVisible(R.id.btn_join, !mSelfCollect && !item.isIn())
                        .setVisible(R.id.btn_more, !mSelfCollect && item.isIn())
                        .setVisible(R.id.iv_collect, mSelfCollect)
                        .setText(R.id.tv_like, like1 == 0 ? "点赞" : NumberUtil.parseToK(like1))
                        .setText(R.id.tv_comment, comment1 == 0 ? "评论" : NumberUtil.parseToK(comment1))
                        .addOnClickListener(R.id.tv_trigger)
                        .addOnClickListener(R.id.iv_star)
                        .addOnClickListener(R.id.tv_name)
                        .addOnClickListener(R.id.btn_join)
                        .addOnClickListener(R.id.btn_more)
                        .addOnClickListener(R.id.iv_collect)
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
                handleText(tvText, item.getText(), item);

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
                int like2 = item.getLike();
                int comment2 = item.getComment();
                helper.setText(R.id.tv_name, item.getStarName())
                        .setText(R.id.tv_time, item.getPublishTime())
                        .setVisible(R.id.btn_join, !mSelfCollect && !item.isIn())
                        .setVisible(R.id.btn_more, !mSelfCollect && item.isIn())
                        .setVisible(R.id.iv_collect, mSelfCollect)
                        .setText(R.id.tv_text, item.getText())
                        .setText(R.id.tv_author, item.getsAuthorName())
                        .setText(R.id.tv_origin_star, item.getsStarName())
                        .setText(R.id.tv_publisher, item.getAuthorName())
                        .setText(R.id.tv_like, like2 == 0 ? "点赞" : NumberUtil.parseToK(like2))
                        .setText(R.id.tv_comment, comment2 == 0 ? "评论" : NumberUtil.parseToK(comment2))
                        .addOnClickListener(R.id.tv_trigger)
                        .addOnClickListener(R.id.iv_star)
                        .addOnClickListener(R.id.tv_name)
                        .addOnClickListener(R.id.btn_join)
                        .addOnClickListener(R.id.btn_more)
                        .addOnClickListener(R.id.iv_collect)
                        .addOnClickListener(R.id.tv_text)
                        .addOnClickListener(R.id.tv_origin_star)
                        .addOnClickListener(R.id.tv_source_text)
                        .addOnClickListener(R.id.layout_link)
                        .addOnClickListener(R.id.iv_publisher)
                        .addOnClickListener(R.id.tv_publisher)
                        .addOnClickListener(R.id.tv_like)
                        .addOnClickListener(R.id.tv_comment)
                        .addOnClickListener(R.id.tv_share);

                /**
                 * 加载头像
                 */
                GlideLoader.loadRound(mContext, item.getImgStarUrl(), helper.getView(R.id.iv_star), 8);
                GlideLoader.loadCircle(mContext, item.getImgAuthorUrl(), helper.getView(R.id.iv_publisher));

                /**
                 * 设置正文：1.“全文”跳转；2.超链接 跳转
                 */
                AllTextView tvDynamic = helper.getView(R.id.tv_text);
                handleText(tvDynamic, item.getText(), item);

                /**
                 * 原动态是否已删除
                 */
                boolean sHasDelete = item.getsHasDelete();
                RelativeLayout sourceExist = helper.getView(R.id.layout_source_exist);
                AppCompatTextView tvDelete = helper.getView(R.id.tv_null);
                tvDelete.setVisibility(sHasDelete ? View.VISIBLE : View.GONE);
                sourceExist.setVisibility(sHasDelete ? View.GONE : View.VISIBLE);

                if (!sHasDelete) {

                    /**
                     * 设置转发正文：1.“全文”跳转；2.超链接 跳转
                     */
                    AllTextView tvSource = helper.getView(R.id.tv_source_text);
                    handleText(tvSource, item.getsText(), item);

                    /**
                     * 设置转发：九宫格图片
                     */
                    NineGridImageView<String> sourceNine = helper.getView(R.id.iv_nine);
                    if (null != item.getsImageUrls()) {
                        sourceNine.setAdapter(new NineImageAdapter());
                        sourceNine.setImagesData(item.getsImageUrls());
                        // TODO: 2018/8/9 可去掉
                        sourceNine.setVisibility(View.VISIBLE);
                    } else {
                        sourceNine.setVisibility(View.GONE);
                    }

                    /**
                     * 设置转发：链接布局的内容
                     */
                    boolean sourceLink = !TextUtils.isEmpty(item.getsLinkUrl());
                    LinearLayout layoutSourceLink = helper.getView(R.id.layout_link);
                    if (sourceLink) {
                        GlideLoader.load(mContext, item.getsLinkImage(), helper.getView(R.id.iv_link));
                        helper.setText(R.id.tv_link, item.getsLinkContent());
                        layoutSourceLink.setVisibility(View.VISIBLE);
                    } else {
                        layoutSourceLink.setVisibility(View.GONE);
                    }
                }

                /**
                 * 设置点赞、收藏
                 */
                int action2 = item.getTriggerAction();
                RelativeLayout layoutAction = helper.getView(R.id.layout_action);
                layoutAction.setVisibility(action2 <= DynamicOut.TRIGGER_ACTION_COLLECT ? View.VISIBLE : View.GONE);
                if (action2 <= DynamicOut.TRIGGER_ACTION_COLLECT) {
                    helper.setText(R.id.tv_trigger, item.getTriggerName())
                            .setText(R.id.tv_action, action2 == DynamicOut.TRIGGER_ACTION_LIKE ? "点赞了" : "收藏了");
                }

                // TODO: 2018/8/9 添加测试数据， 添加点击事件
                break;
            default:
                break;
        }
    }

    /**
     * 设置正文：1.“全文”跳转；2.超链接 跳转
     */
    @SuppressLint("ClickableViewAccessibility")
    private void handleText(AllTextView textView, String raw, DynamicOut dynamic) {
        List<String> urls = new ArrayList<>();
        String text = UrlUtil.handleURLinString(raw, urls);
        SpannableString ss = new SpannableString(text);
        int loc = text.indexOf("#查看链接");
        int i = 0;
        while (loc != -1) {
            InnerURLSpan innerURLSpan = new InnerURLSpan(mContext, "", urls.get(i++));
            ss.setSpan(innerURLSpan, loc, loc + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            loc = text.indexOf("#查看链接", loc + 1);
        }

        textView.setText(ss);
        textView.setOnTouchListener(FilterClickMovementMethod.getInstance());
        textView.setMaxShowLines(6);
        textView.setMyText(textView.getText());
        textView.setOnAllSpanClickListener(widget -> ARouter.getInstance()
                .build(dynamic.getType().equals("1") ? RouterMap.PATH_DYNAMIC_CONNATE_ACTIVITY
                        : RouterMap.PATH_DYNAMIC_FORWARD_ACTIVITY)
                .withString(DynamicForwardActivity.DYNAMIC_ID, dynamic.getDynamicId())
                .navigation());
    }
}
