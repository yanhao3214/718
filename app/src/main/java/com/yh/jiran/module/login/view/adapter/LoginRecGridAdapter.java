package com.yh.jiran.module.login.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yh.core.utils.NumberUtil;
import com.yh.jiran.R;
import com.yh.jiran.module.home.model.entity.HotStar;
import com.yh.jiran.utils.Consts;
import com.yh.jiran.utils.image.GlideLoader;
import com.yh.ui.img.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 闫昊
 * @date: 2018/8/3
 * @function: 登录-推荐星球：适配器
 */
public class LoginRecGridAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<HotStar> mStars;
    private RequestOptions mOptions;

    public LoginRecGridAdapter(Context context, List<HotStar> stars) {
        mContext = context;
        mStars = stars;
        mInflater = LayoutInflater.from(mContext);
        mOptions = new RequestOptions().centerCrop().transform(new GlideRoundTransform(mContext));
    }

    @Override
    public int getCount() {
        return mStars.size();
    }

    @Override
    public Object getItem(int i) {
        return mStars.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        HotStar star = mStars.get(i);
        if (view == null) {
            view = mInflater.inflate(R.layout.item_rec_star_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(star.getName());
        holder.tvNumber.setText(NumberUtil.parseToK(star.getMember()));
        GlideLoader.loadRound(mContext, Consts.BING_PIC, holder.ivImage);
        holder.checkJoin.setChecked(star.isIn());
        holder.checkJoin.setOnClickListener(view1 -> {
            holder.checkJoin.toggle();
            star.setIn(!star.isIn());
            // TODO: 2018/8/6 发送加入或退出消息
        });
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.iv_image)
        AppCompatImageView ivImage;
        @BindView(R.id.tv_number)
        AppCompatTextView tvNumber;
        @BindView(R.id.tv_name)
        AppCompatTextView tvName;
        @BindView(R.id.check_join)
        CheckedTextView checkJoin;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
