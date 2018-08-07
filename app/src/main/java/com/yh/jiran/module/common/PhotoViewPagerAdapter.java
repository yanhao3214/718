package com.yh.jiran.module.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.yh.jiran.utils.image.GlideLoader;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/7/4 0004
 * @function: 大图浏览适配器
 */
public class PhotoViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mList;

    public PhotoViewPagerAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView photo = new PhotoView(mContext);
        GlideLoader.load(mContext, mList.get(position), photo);
        container.addView(photo, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        return photo;
    }
}
