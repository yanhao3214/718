package com.yh.jiran.module.dynamic.model.entity;

import java.util.List;

/**
 * @author: 闫昊
 * @date: 2018/8/7
 * @function: 星球外动态
 */
public class DynamicOut {

    /**
     * 动态类型：自建
     */
    public final String DYNAMIC_TYPE_CONNATE = "1";

    /**
     * 动态类型：转发
     */
    public final String DYNAMIC_TYPE_FORWARD = "2";

    /**
     * 点赞
     */
    public static final int TRIGGER_ACTION_LIKE = 1;

    /**
     * 点赞、评论
     */
    public static final int TRIGGER_ACTION_LIKE_COMMENT = 2;

    /**
     * 收藏
     */
    public static final int TRIGGER_ACTION_COLLECT = 3;

    /**
     * 关注星球
     */
    public static final int TRIGGER_ACTION_CONCERN_STAR = 4;

    /**
     * 关注用户
     */
    public static final int TRIGGER_ACTION_CONCERN_USER = 5;


    private String dynamicId;
    private String creatorId;
    private String starId;
    private String triggerId;
    private Long dynamicStar;
    private int dynamicClassify;
    private int visibleRange;

    public String type;
    private String imgStarUrl;
    private String starName;
    private String publishTime;
    private boolean isIn;
    private String text;
    private String imgAuthorUrl;
    private String authorName;
    private int like;
    private int comment;
    private List<String> imgUrl;
    private String linkUrl;
    private boolean hasDeleted;
    private String triggerName;

    /**
     * 1:点赞动态 2:点赞评论 3:收藏 4.关注星球 5.关注用户
     */
    private int triggerAction;

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getStarId() {
        return starId;
    }

    public void setStarId(String starId) {
        this.starId = starId;
    }

    public String getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(String triggerId) {
        this.triggerId = triggerId;
    }

    public Long getDynamicStar() {
        return dynamicStar;
    }

    public void setDynamicStar(Long dynamicStar) {
        this.dynamicStar = dynamicStar;
    }

    public int getDynamicClassify() {
        return dynamicClassify;
    }

    public void setDynamicClassify(int dynamicClassify) {
        this.dynamicClassify = dynamicClassify;
    }

    public int getVisibleRange() {
        return visibleRange;
    }

    public void setVisibleRange(int visibleRange) {
        this.visibleRange = visibleRange;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgStarUrl() {
        return imgStarUrl;
    }

    public void setImgStarUrl(String imgStarUrl) {
        this.imgStarUrl = imgStarUrl;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public boolean isIn() {
        return isIn;
    }

    public void setIn(boolean in) {
        isIn = in;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgAuthorUrl() {
        return imgAuthorUrl;
    }

    public void setImgAuthorUrl(String imgAuthorUrl) {
        this.imgAuthorUrl = imgAuthorUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public boolean isHasDeleted() {
        return hasDeleted;
    }

    public void setHasDeleted(boolean hasDeleted) {
        this.hasDeleted = hasDeleted;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public int getTriggerAction() {
        return triggerAction;
    }

    public void setTriggerAction(int triggerAction) {
        this.triggerAction = triggerAction;
    }
}
