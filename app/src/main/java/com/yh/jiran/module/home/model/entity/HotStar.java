package com.yh.jiran.module.home.model.entity;

/**
 * @author: 闫昊
 * @date: 2018/8/2
 * @function: 热门星球
 */
public class HotStar {
    private Long id;
    private String name;
    private int member;
    private boolean isIn;
    private String imgUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }

    public boolean isIn() {
        return isIn;
    }

    public void setIn(boolean in) {
        isIn = in;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "HotStar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", member=" + member +
                ", isIn=" + isIn +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
