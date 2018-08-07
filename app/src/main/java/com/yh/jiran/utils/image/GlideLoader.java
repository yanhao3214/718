package com.yh.jiran.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yh.ui.img.GlideCircleTransform;
import com.yh.ui.img.GlideRoundTransform;

import java.io.File;

/**
 * @author: 闫昊
 * @date: 2018/8/6
 * @function: Glide图片加载器
 */
public class GlideLoader {

    public static void loadCircle(Context context, String url, ImageView imageView) {
        RequestOptions circle = new RequestOptions().centerCrop().transform(new GlideCircleTransform(context));
        Glide.with(context).load(url).apply(circle).into(imageView);
    }

    public static void loadCircle(Context context, Bitmap bitmap, ImageView imageView) {
        RequestOptions circle = new RequestOptions().centerCrop().transform(new GlideCircleTransform(context));
        Glide.with(context).load(bitmap).apply(circle).into(imageView);
    }

    public static void loadCircle(Context context, File file, ImageView imageView) {
        RequestOptions circle = new RequestOptions().centerCrop().transform(new GlideCircleTransform(context));
        Glide.with(context).load(file).apply(circle).into(imageView);
    }

    public static void loadRound(Context context, String url, ImageView imageView, int radius) {
        RequestOptions circle = new RequestOptions().centerCrop().transform(new GlideRoundTransform(context, radius));
        Glide.with(context).load(url).apply(circle).into(imageView);
    }

    public static void loadRound(Context context, String url, ImageView imageView) {
        RequestOptions round = new RequestOptions().centerCrop().transform(new GlideRoundTransform(context, 2));
        Glide.with(context).load(url).apply(round).into(imageView);
    }
}
