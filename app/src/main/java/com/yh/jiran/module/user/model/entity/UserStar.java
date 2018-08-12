package com.yh.jiran.module.user.model.entity;

/**
 * @author: 闫昊
 * @date: 2018/8/11
 * @function: 个人中心星球数据
 */
public class UserStar {
    private String starId;
    private String starName;
    private String starImage;
    private String status;
    private String ownerName;
    private String starDesc;
    private int eliteDynamic;
    private int member;

    /**
     * 是否开启推送
     */
    private boolean push;

    /**
     * 是否在星球中
     */
    private boolean isIn;

    /**
     * 审核中
     */
    private boolean inCheck;

    public boolean isInCheck() {
        return inCheck;
    }

    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }

    public String getStarId() {
        return starId;
    }

    public void setStarId(String starId) {
        this.starId = starId;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public String getStarImage() {
        return starImage;
    }

    public void setStarImage(String starImage) {
        this.starImage = starImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getStarDesc() {
        return starDesc;
    }

    public void setStarDesc(String starDesc) {
        this.starDesc = starDesc;
    }

    public int getEliteDynamic() {
        return eliteDynamic;
    }

    public void setEliteDynamic(int eliteDynamic) {
        this.eliteDynamic = eliteDynamic;
    }

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }

    public boolean isPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }

    public boolean isIn() {
        return isIn;
    }

    public void setIn(boolean in) {
        isIn = in;
    }
}
