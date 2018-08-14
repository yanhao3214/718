package com.yh.jiran.module.user.view.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yh.jiran.R;
import com.yh.jiran.module.user.model.entity.UserMutual;
import com.yh.jiran.utils.image.GlideLoader;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/8/13
 * @function: 关注、粉丝列表适配器
 */
public class UserMutualAdapter extends BaseQuickAdapter<UserMutual, BaseViewHolder> {
    private List<UserMutual> mDatas;
    private boolean mOtherView;

    public UserMutualAdapter(@Nullable List<UserMutual> data) {
        super(R.layout.item_user_mutual_layout, data);
        mData = data;
        mOtherView = false;
    }

    public UserMutualAdapter(@Nullable List<UserMutual> data, boolean otherView) {
        this(data);
        mOtherView = otherView;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserMutual item) {
        helper.setText(R.id.tv_name, item.getUserName())
                .setText(R.id.tv_desc, item.getDesc())
                .addOnClickListener(R.id.tv_done)
                .addOnClickListener(R.id.tv_operate)
                .addOnClickListener(R.id.tv_mutual);
        GlideLoader.load(mContext, item.getImgUrl(), helper.getView(R.id.iv_user));

        AppCompatTextView tvDone = helper.getView(R.id.tv_done);
        AppCompatTextView tvOperate = helper.getView(R.id.tv_operate);
        AppCompatTextView tvMutual = helper.getView(R.id.tv_mutual);
        boolean beConcerned = item.isBeConcerned();
        boolean concernMe = item.isConcernMe();
        if (mOtherView) {
            tvOperate.setVisibility(beConcerned ? View.INVISIBLE : View.VISIBLE);
            tvDone.setVisibility(beConcerned ? (concernMe ? View.INVISIBLE : View.VISIBLE) : View.INVISIBLE);
            tvMutual.setVisibility(beConcerned ? (concernMe ? View.VISIBLE : View.INVISIBLE) : View.INVISIBLE);
        } else {
            tvOperate.setVisibility(View.INVISIBLE);
            tvDone.setVisibility(concernMe ? View.INVISIBLE : View.VISIBLE);
            tvMutual.setVisibility(concernMe ? View.VISIBLE : View.INVISIBLE);
        }
    }
}
