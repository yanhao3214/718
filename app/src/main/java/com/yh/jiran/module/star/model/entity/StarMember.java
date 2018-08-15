package com.yh.jiran.module.star.model.entity;

/**
 * @author: 闫昊
 * @date: 2018/8/15
 * @function: 星球成员（成员、主理人、嘉宾、禁言名单）
 */
public class StarMember {
    private String userId;
    private String userName;
    private String status;
    private String imgUrl;

    /**
     * 禁言类型：
     * 0 == 解禁/m=没有被禁言
     * 1 == 禁言3天
     * 2 == 永久禁言
     */
    private int muteType;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getMuteType() {
        return muteType;
    }

    public void setMuteType(int muteType) {
        this.muteType = muteType;
    }

    @Override
    public String toString() {
        return "StarMember{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", status='" + status + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", muteType=" + muteType +
                '}';
    }
}
