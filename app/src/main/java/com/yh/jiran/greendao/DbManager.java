package com.yh.jiran.greendao;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author: 闫昊
 * @date: 2018/7/30
 * @function: 数据库管理类
 */
public class DbManager {

    private static final String DATA_BASE_NAME = "jiran.db";
    private DaoSession mDaoSession = null;

    private DbManager() {
    }

    public DbManager init(Context context) {
        initDao(context);
        return this;
    }

    public static DbManager getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final DbManager INSTANCE = new DbManager();
    }

    private void initDao(Context context) {
        final DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context, DATA_BASE_NAME);
        final Database db = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public final TestUserDao getTestUserDao() {
        return mDaoSession.getTestUserDao();
    }
}
