package com.yh.jiran.module.dynamic.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Path;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yh.jiran.R;
import com.yh.jiran.custom.img.SquareImageView;
import com.yh.jiran.module.dynamic.view.TweetActivity;
import com.yh.jiran.module.dynamic.view.callback.OnPicDeleteListener;
import com.yh.jiran.utils.image.GlideLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yh.jiran.module.dynamic.view.TweetActivity.TWEET_PICS_COUNT;

/**
 * @author: 闫昊
 * @date: 2018/8/18
 * @function:
 */
public class TweetPicGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<Object> mData;
    private LayoutInflater mInflater;
    private OnPicDeleteListener mListener;

    public TweetPicGridAdapter(Context context, List<Object> data) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mData = data;
//        if (data.size() > TWEET_PICS_COUNT) {
//            mData = data.subList(0, TWEET_PICS_COUNT - 1);
//        } else {
//            mData = data;
//        }
    }

//    /**
//     * 控制图片最大个数：9
//     * 刷新列表
//     */
//    public void refresh(List<Object> data) {
//        if (data.size() > TWEET_PICS_COUNT) {
//            mData = data.subList(0, TWEET_PICS_COUNT);
//        } else {
//            mData = data;
//        }
//        notifyDataSetChanged();
//    }

    public void setOnPicDeleteListener(OnPicDeleteListener onPicDeleteListener) {
        mListener = onPicDeleteListener;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object data = mData.get(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_tweet_pics_grid_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ivDelete.setVisibility(data instanceof Integer ? View.GONE : View.VISIBLE);
        if (data instanceof Uri) {
            GlideLoader.load(mContext, (Uri) data, holder.ivPic);
        } else if (data instanceof Integer) {
            GlideLoader.load(mContext, (Integer) data, holder.ivPic);
        }

        holder.ivDelete.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onDelete(position);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_pic)
        SquareImageView ivPic;
        @BindView(R.id.iv_delete)
        AppCompatImageView ivDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
