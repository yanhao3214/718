package com.yh.jiran.module.home.view.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yh.jiran.R;
import com.yh.jiran.module.home.model.entity.HomeStar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 闫昊
 * @date: 2018/7/27
 * @function:
 */
public class StarPickAdapter extends BaseAdapter {
    private List<HomeStar> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public StarPickAdapter(Context context, List<HomeStar> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HomeStar star = mList.get(i);
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_star_pick_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.ivStar.setImageResource(R.drawable.ic_user_male);
        holder.tvTitle.setText(star.getTitle());
        holder.tvDesc.setText(star.getOwner());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.iv_star)
        AppCompatImageView ivStar;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_desc)
        TextView tvDesc;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
