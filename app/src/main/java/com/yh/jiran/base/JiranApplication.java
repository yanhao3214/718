package com.yh.jiran.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mob.MobSDK;
import com.yh.jiran.greendao.DaoMaster;
import com.yh.jiran.greendao.DaoSession;
import com.yh.jiran.greendao.DbManager;
import com.yh.jiran.share.ShareManager;

import org.greenrobot.greendao.database.Database;

/**
 * @author: 闫昊
 * @date: 2018/7/21
 * @function:
 */
public class JiranApplication extends Application {
    private static JiranApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        initARouter();
        DbManager.getInstance().init(mApplication);
        ShareManager.getInstance().init(mApplication);
    }

    private void initARouter() {
        if (isDebug()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(mApplication);
    }

    public boolean isDebug() {
        return true;
    }
}
