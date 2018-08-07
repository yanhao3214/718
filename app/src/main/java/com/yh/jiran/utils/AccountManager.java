package com.yh.jiran.utils;

import com.yh.jiran.greendao.DbManager;
import com.yh.jiran.greendao.UserDao;
import com.yh.jiran.module.login.model.entity.User;

/**
 * @author: 闫昊
 * @date: 2018/8/4
 * @function: 用户管理类
 */
public class AccountManager {

    public static AccountManager getInstance() {
        return Holder.INSTANCE;
    }

    private AccountManager() {
    }

    private static class Holder {
        private static final AccountManager INSTANCE = new AccountManager();
    }

    public void createFakeUser() {
        boolean isFirst = true;
        if (isFirst) {
            User user1 = new User("yh001", "yanhao1", "17372754609", false, false, false);
            DbManager.getInstance().getUserDao().save(user1);
            User user2 = new User("yh002", "yanhao2", "17372754609", false, false, true);
            DbManager.getInstance().getUserDao().save(user2);
            User user3 = new User("yh003", "yanhao3", "17372754609", false, true, true);
            DbManager.getInstance().getUserDao().save(user3);
            User user4 = new User("yh004", "yanhao4", "17372754609", true, true, true);
            DbManager.getInstance().getUserDao().save(user4);
            User user5 = new User("yh005", "yanhao5", "17372754609", true, false, false);
            DbManager.getInstance().getUserDao().save(user5);
            User user6 = new User("yh006", "yanhao6", "17372754609", true, true, false);
            DbManager.getInstance().getUserDao().save(user6);
            isFirst = false;
        }
    }

    public User getCurrentUser() {
        User currentUser = DbManager.getInstance().getUserDao().queryBuilder()
                .where(UserDao.Properties.UserId.eq("yh004")).unique();
        return new User();
    }
}
