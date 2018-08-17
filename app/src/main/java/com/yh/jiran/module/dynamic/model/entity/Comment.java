package com.yh.jiran.module.dynamic.model.entity;

/**
 * @author: 闫昊
 * @date: 2018/8/16
 * @function: 动态详情-评论
 */
public class Comment {

    private String id;
    private boolean isMine;
    private String userId;
    private String userName;
    private String userImage;
    private String text;
    private String status;
    private boolean hasDeleted;
    private int like;
    private String time;

    private String sId;
    private String sUserId;
    private String sUserName;
    private String sText;
    private boolean sHasDeleted;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

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

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isHasDeleted() {
        return hasDeleted;
    }

    public void setHasDeleted(boolean hasDeleted) {
        this.hasDeleted = hasDeleted;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getsUserId() {
        return sUserId;
    }

    public void setsUserId(String sUserId) {
        this.sUserId = sUserId;
    }

    public String getsUserName() {
        return sUserName;
    }

    public void setsUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getsText() {
        return sText;
    }

    public void setsText(String sText) {
        this.sText = sText;
    }

    public boolean issHasDeleted() {
        return sHasDeleted;
    }

    public void setsHasDeleted(boolean sHasDeleted) {
        this.sHasDeleted = sHasDeleted;
    }
}
