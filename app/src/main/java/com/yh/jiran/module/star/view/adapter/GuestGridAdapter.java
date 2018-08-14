package com.yh.jiran.module.star.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yh.jiran.R;
import com.yh.jiran.module.star.model.entity.Guest;
import com.yh.jiran.utils.image.GlideLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author: 闫昊
 * @date: 2018/7/26
 * @function: 星球资料-星球嘉宾 嘉宾列表适配器
 */
public class GuestGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<Guest> mDatas;
    private LayoutInflater mInflater;

    public GuestGridAdapter(Context context, List<Guest> guests) {
        mContext = context;
        mDatas = guests;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Guest guest = mDatas.get(i);
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_grid_star_guest_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(guest.getUserName());
        GlideLoader.load(mContext, guest.getImgUrl(), holder.ivImage);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.iv_image)
        CircleImageView ivImage;
        @BindView(R.id.tv_name)
        AppCompatTextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
