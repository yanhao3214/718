package com.yh.jiran.module.user.model.entity;

/**
 * @author: 闫昊
 * @date: 2018/8/13
 * @function: 互动用户Bean
 */
public class UserMutual {
    private String userId;
    private String userName;
    private String imgUrl;
    private String desc;
    private boolean beConcerned;
    private boolean concernMe;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isBeConcerned() {
        return beConcerned;
    }

    public void setBeConcerned(boolean beConcerned) {
        this.beConcerned = beConcerned;
    }

    public boolean isConcernMe() {
        return concernMe;
    }

    public void setConcernMe(boolean concernMe) {
        this.concernMe = concernMe;
    }

    @Override
    public String toString() {
        return "UserMutual{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", desc='" + desc + '\'' +
                ", beConcerned=" + beConcerned +
                ", concernMe=" + concernMe +
                '}';
    }
}
