package com.yh.core.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function:
 */
public class GlideLoader implements ILoader {
    @Override
    public void init(Context context) {

    }

    @Override
    public void loadNet(ImageView target, String url) {
        loadNet(target, url, null);
    }

    @Override
    public void loadNet(ImageView target, String url, RequestOptions options) {
        Glide.with(target.getContext()).load(url).apply(options).into(target);
    }

    @Override
    public void loadResource(ImageView target, int resId) {
        loadResource(target, resId, null);
    }

    @Override
    public void loadResource(ImageView target, int resId, RequestOptions options) {
        Glide.with(target.getContext()).load(resId).apply(options).into(target);
    }

    @Override
    public void loadAssets(ImageView target, String assetName) {
        loadAssets(target, assetName, null);
    }

    @Override
    public void loadAssets(ImageView target, String assetName, RequestOptions options) {
        Glide.with(target.getContext()).load("file:///android_asset/" + assetName).apply(options).into(target);
    }

    @Override
    public void loadFile(ImageView target, File file) {
        loadFile(target, file, null);
    }

    @Override
    public void loadFile(ImageView target, File file, RequestOptions options) {
        Glide.with(target.getContext()).load(file).apply(options).into(target);
    }

    @Override
    public void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    private void load(RequestOptions request, ImageView target, Options options) {
        if (options == null) {
            options = Options.defaultOptions();
        }
        if (options.loadingResId != Options.RES_NONE) {
            request.placeholder(options.loadingResId);
        }
        if (options.loadErrorResId != Options.RES_NONE) {
            request.error(options.loadErrorResId);
        }
    }
}
