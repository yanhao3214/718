package com.yh.jiran.module.home.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yh.jiran.R;
import com.yh.jiran.module.home.model.entity.MineStar;

import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 闫昊
 * @date: 2018/7/26
 * @function:
 */
public class StarGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<MineStar> stars;
    private LayoutInflater mInflater;
    @BindDrawable(R.drawable.bg_star_status_owner)
    Drawable drawableOwner;
    @BindDrawable(R.drawable.bg_star_status_guest)
    Drawable drawableGuest;
    @BindDrawable(R.drawable.bg_star_status_member)
    Drawable drawableMember;

    public StarGridAdapter(Context context, List<MineStar> stars) {
        mContext = context;
        this.stars = stars;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return stars.size();
    }

    @Override
    public Object getItem(int i) {
        return stars.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        MineStar star = stars.get(i);
        if (view == null) {
            view = mInflater.inflate(R.layout.item_mine_star_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.ivStar.setImageResource(R.drawable.xxx2);
        holder.ivNew.setVisibility(star.isHasNew() ? View.VISIBLE : View.GONE);
        holder.tvStatus.setText(star.getStatus());
        switch (star.getStatus()) {
            case "主理人":
                holder.tvStatus.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bg_star_status_owner));
                break;
            case "嘉宾":
                holder.tvStatus.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bg_star_status_guest));
                break;
            default:
                holder.tvStatus.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bg_star_status_member));
                break;
        }
        holder.tvTitle.setText(star.getTitle());
        holder.tvOwner.setText(star.getOwner());
        return view;
    }

    class ViewHolder {
        @BindView(R.id.iv_star)
        AppCompatImageView ivStar;
        @BindView(R.id.iv_new)
        AppCompatImageView ivNew;
        @BindView(R.id.tv_status)
        AppCompatTextView tvStatus;
        @BindView(R.id.tv_title)
        AppCompatTextView tvTitle;
        @BindView(R.id.tv_owner)
        AppCompatTextView tvOwner;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
