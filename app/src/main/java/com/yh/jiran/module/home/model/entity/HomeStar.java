package com.yh.jiran.module.home.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author: 闫昊
 * @date: 2018/7/26
 * @function: 首页-我的：星球信息
 */
@Entity(nameInDb = "home_star")
public class HomeStar {
    @Id
    private long starId;
    private String imgUrl;
    private String icUrl;
    private String title;
    private String status;
    private String owner;
    private int member;
    private boolean hasNew;

    @Generated(hash = 323570817)
    public HomeStar(long starId, String imgUrl, String icUrl, String title,
            String status, String owner, int member, boolean hasNew) {
        this.starId = starId;
        this.imgUrl = imgUrl;
        this.icUrl = icUrl;
        this.title = title;
        this.status = status;
        this.owner = owner;
        this.member = member;
        this.hasNew = hasNew;
    }

    @Generated(hash = 1529506854)
    public HomeStar() {
    }

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

    public String getIcUrl() {
        return icUrl;
    }

    public void setIcUrl(String icUrl) {
        this.icUrl = icUrl;
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

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }

    public boolean isHasNew() {
        return hasNew;
    }

    public void setHasNew(boolean hasNew) {
        this.hasNew = hasNew;
    }

    public boolean getHasNew() {
        return this.hasNew;
    }

    @Override
    public String toString() {
        return "HomeStar{" +
                "starId=" + starId +
                ", imgUrl='" + imgUrl + '\'' +
                ", icUrl='" + icUrl + '\'' +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", owner='" + owner + '\'' +
                ", member=" + member +
                ", hasNew=" + hasNew +
                '}';
    }
}
