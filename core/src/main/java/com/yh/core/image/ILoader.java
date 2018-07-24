package com.yh.core.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.yh.core.app.JConfig;

import java.io.File;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function: 图片加载接口,可以有不同的实现：GlideLoader/PicassoLoader/FrescoLoader等，随意切换
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

    class Options {

        public static final int RES_NONE = -1;
        public int loadingResId = RES_NONE;
        public int loadErrorResId = RES_NONE;

        public static Options defaultOptions() {
            return new Options(JConfig.IL_LOADING_RES, JConfig.IL_ERROR_RES);
        }

        public Options(int loadingResId, int loadErrorResId) {
            this.loadingResId = loadingResId;
            this.loadErrorResId = loadErrorResId;
        }
    }
}
