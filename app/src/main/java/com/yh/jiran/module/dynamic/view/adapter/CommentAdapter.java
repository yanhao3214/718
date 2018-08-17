package com.yh.jiran.module.dynamic.view.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yh.core.utils.NumberUtil;
import com.yh.jiran.R;
import com.yh.jiran.custom.text.FilterClickMovementMethod;
import com.yh.jiran.custom.text.SmartSpan;
import com.yh.jiran.module.dynamic.model.entity.Comment;
import com.yh.jiran.utils.RouterMap;
import com.yh.jiran.utils.image.GlideLoader;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/8/16
 * @function: 动态详情-评论列表适配器
 */
public class CommentAdapter extends BaseQuickAdapter<Comment, BaseViewHolder> {
    private boolean mHostView;

    public CommentAdapter(@Nullable List<Comment> data, boolean hostView) {
        super(R.layout.item_dynamic_comment_layout, data);
        mHostView = hostView;
    }

    public CommentAdapter(@Nullable List<Comment> data) {
        this(data, false);
    }

    @Override
    protected void convert(BaseViewHolder helper, Comment item) {
        helper.setText(R.id.tv_name, item.getUserName())
                .setText(R.id.tv_time, item.getTime())
                .setText(R.id.tv_like_num, NumberUtil.parseToK(item.getLike()))
                .setText(R.id.tv_comment_text, item.getText())
                .setVisible(R.id.tv_delete, mHostView || item.isMine())
                .addOnClickListener(R.id.iv_reviewer)
                .addOnClickListener(R.id.iv_like)
                .addOnClickListener(R.id.tv_comment_text)
                .addOnClickListener(R.id.tv_source_text)
                .addOnClickListener(R.id.tv_reply)
                .addOnClickListener(R.id.tv_delete);

        GlideLoader.load(mContext, item.getUserImage(), helper.getView(R.id.iv_reviewer));

        /**
         * 设置上级评论
         */
        AppCompatTextView tvSourceText = helper.getView(R.id.tv_source_text);
        AppCompatTextView tvSourceDeleted = helper.getView(R.id.tv_source_deleted);
        if (TextUtils.isEmpty(item.getsId())) {
            tvSourceText.setVisibility(View.GONE);
            tvSourceDeleted.setVisibility(View.GONE);
        } else {
            if (!item.issHasDeleted()) {
                handleText(item.getsUserName(), item.getsText(), helper.getView(R.id.tv_source_text));
            }
            tvSourceText.setVisibility(item.issHasDeleted() ? View.GONE : View.VISIBLE);
            tvSourceDeleted.setVisibility(item.issHasDeleted() ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 设置“@用户名”超链接
     *
     * @param userName 用户名
     * @param text     评论内容
     * @param textView
     */
    @SuppressLint("ClickableViewAccessibility")
    private void handleText(String userName, String text, TextView textView) {
        StringBuilder sb = new StringBuilder("@");
        sb.append(userName).append("：").append(text);
        int loc = userName.length() + 2;
        SpannableString ss = new SpannableString(sb);
        SmartSpan smartSpan = new SmartSpan(mContext, () -> ARouter.getInstance()
                .build(RouterMap.PATH_USER_HOME_ACTIVITY)
                .navigation());
        ss.setSpan(smartSpan, 0, loc, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setOnTouchListener(FilterClickMovementMethod.getInstance());
    }
}
