package com.yh.jiran.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yh.jiran.greendao.DaoMaster;
import com.yh.jiran.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * @author: 闫昊
 * @date: 2018/7/21
 * @function:
 */
public class JiranApplication extends Application {
    private static final String DATA_BASE_NAME = "JIRAN_DB";
    private static JiranApplication mApplication = null;
    private static DaoSession mDaoSession = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        initARouter();
        initDB();
    }

    private void initDB() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(mApplication, DATA_BASE_NAME);
        Database db = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
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
