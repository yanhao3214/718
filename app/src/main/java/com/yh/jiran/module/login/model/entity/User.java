package com.yh.jiran.module.login.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @author: 闫昊
 * @date: 2018/8/4
 * @function: 用户信息
 */
@Entity(nameInDb = "user_info")
public class User {
    @Unique
    private String userId;
    @Unique
    private String userName;
    private String phoneNumber;
    private boolean hasName;
    private boolean hasPortrait;
    private boolean inStar;

    @Generated(hash = 586692638)
    public User() {
    }


    @Generated(hash = 702698381)
    public User(String userId, String userName, String phoneNumber, boolean hasName,
            boolean hasPortrait, boolean inStar) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.hasName = hasName;
        this.hasPortrait = hasPortrait;
        this.inStar = inStar;
    }


    public boolean getHasName() {
        return this.hasName;
    }

    public void setHasName(boolean hasName) {
        this.hasName = hasName;
    }

    public boolean getHasPortrait() {
        return this.hasPortrait;
    }

    public void setHasPortrait(boolean hasPortrait) {
        this.hasPortrait = hasPortrait;
    }

    public boolean getInStar() {
        return this.inStar;
    }

    public void setInStar(boolean inStar) {
        this.inStar = inStar;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", hasName=" + hasName +
                ", hasPortrait=" + hasPortrait +
                ", inStar=" + inStar +
                '}';
    }


    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
