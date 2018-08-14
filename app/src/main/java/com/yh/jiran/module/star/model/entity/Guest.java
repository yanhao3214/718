package com.yh.jiran.module.star.model.entity;

/**
 * @author: 闫昊
 * @date: 2018/8/14
 * @function: 星球嘉宾
 */
public class Guest {
    private String userId;
    private String userName;
    private String imgUrl;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "userId='" + userId + '\'' +
                ", userNma='" + userName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
