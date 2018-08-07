package com.yh.jiran.module.dynamic.view.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.yh.jiran.module.common.PhotoViewActivity;
import com.yh.jiran.utils.Paths;
import com.yh.jiran.utils.image.GlideLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/8/7
 * @function: 动态-九宫格图片 适配器
 */
public class NineImageAdapter extends NineGridImageViewAdapter<String> {

    @Override
    protected void onDisplayImage(Context context, ImageView imageView, String s) {
        GlideLoader.load(context, s, imageView);
    }

    @Override
    protected ImageView generateImageView(Context context) {
        return super.generateImageView(context);
    }

    @Override
    protected void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {
        ARouter
                .getInstance()
                .build(Paths.PATH_PHOTO_VIEW_ACTIVITY)
                .withStringArrayList(PhotoViewActivity.PHOTO_VIEW_URLS, (ArrayList<String>) list)
                .withInt(PhotoViewActivity.PHOTO_VIEW_POSITION, index)
                .navigation();
    }
}
