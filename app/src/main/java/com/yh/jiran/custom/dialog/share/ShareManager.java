package com.yh.jiran.custom.dialog.share;

import android.content.Context;

import com.mob.MobSDK;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * @author: 闫昊
 * @date: 2018/8/1
 * @function: 分享功能统一入口，发送数据到指定平台
 */
public class ShareManager {
    private Platform mPlatform;

    private ShareManager() {
    }

    public static ShareManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final ShareManager INSTANCE = new ShareManager();
    }

    public void shareData(ShareData data, PlatformActionListener listener) {
        switch (data.type) {
            case QQ:
                mPlatform = ShareSDK.getPlatform(QQ.NAME);
                break;
            case QQ_ZONE:
                break;
            case WEICHAT:
                mPlatform = ShareSDK.getPlatform(Wechat.NAME);

                break;
            case WEICHAT_MOMENTS:
                break;
            case SINA_WEIBO:
                mPlatform = ShareSDK.getPlatform(SinaWeibo.NAME);
                break;
            case FACEBOOK:
                break;
            default:
                break;
        }
        mPlatform.setPlatformActionListener(listener);
        mPlatform.share(data.params);
    }

    public void init(Context context) {
        MobSDK.init(context);
    }
}
