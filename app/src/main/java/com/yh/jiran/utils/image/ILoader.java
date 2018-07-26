package com.yh.jiran.utils.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;

import java.io.File;

/**
 * @author: 闫昊
 * @date: 2018/7/26
 * @function:
 */
public interface ILoader {

    void init(Context context);

    void loadNet(ImageView target, String url);

    void loadNet(ImageView target, String url, RequestOptions options);

    void loadResource(ImageView target, int resId);

    void loadResource(ImageView target, int resId, RequestOptions options);

    void loadAssets(ImageView target, String assetName);

    void loadAssets(ImageView target, String assetName, RequestOptions options);

    void loadFile(ImageView target, File file);

    void loadFile(ImageView target, File file, RequestOptions options);

    void clearMemoryCache(Context context);

    void clearDiskCache(Context context);
}
