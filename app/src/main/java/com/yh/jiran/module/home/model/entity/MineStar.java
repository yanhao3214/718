package com.yh.jiran.module.home.model.entity;

/**
 * @author: 闫昊
 * @date: 2018/7/26
 * @function: 首页-我的：星球信息
 */
public class MineStar {
    private long starId;
    private String imgUrl;
    private String title;
    private String status;
    private String owner;
    private boolean hasNew;

    public long getStarId() {
        return starId;
    }

    public void setStarId(long starId) {
        this.starId = starId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isHasNew() {
        return hasNew;
    }

    public void setHasNew(boolean hasNew) {
        this.hasNew = hasNew;
    }

    @Override
    public String toString() {
        return "MineStar{" +
                "starId=" + starId +
                ", imgUrl='" + imgUrl + '\'' +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", owner='" + owner + '\'' +
                ", hasNew=" + hasNew +
                '}';
    }
}
