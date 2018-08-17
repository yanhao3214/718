package com.yh.jiran.module.dynamic.model.entity;

import java.util.Date;
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
    public final String DYNAMIC_TYPE_FORWARD = "1";

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

    /**
     * 动态：Id、正文、是否置顶、是否已删除、是否已收藏
     */
    private String dynamicId;
    private String text;
    private boolean top;
    private boolean hasDelete;
    private boolean hasCollect;

    /**
     * 是否被我收藏
     */
    private boolean myCollection;

    /**
     * 我在这条动态所属星球的身份
     */
    private String myStatus;

    /**
     * 我是否关注了动态作者
     */
    private boolean concernAuthor;

    /**
     * 动态类型（1:自建 2转发）
     */
    public String type;

    /**
     * 分类 (1:精华 2:广场 )
     */
    private int dynamicClassify;

    /**
     * 发布人：Id、头像地址、用户名
     */
    private String authorId;
    private String imgAuthorUrl;
    private String authorName;

    /**
     * 1:点赞动态 2:点赞评论 3:收藏 4.关注星球 5.关注用户
     */
    private int triggerAction;

    /**
     * 触发人：Id、用户名
     */
    private String triggerId;
    private String triggerName;

    /**
     * 可见范围（1:全局可见: 2:星球可见）
     */
    private int visibleRange;


    /**
     * 所属星球：Id、名字、图片地址
     */
    private String starId;
    private String starName;
    private String imgStarUrl;

    /**
     * 动态时间：发布、修改、爬取
     */
    private String publishTime;
    private String upDateTime;
    private String issueTime;

    /**
     * 抓取来源：名字
     */
    private String sourceName;

    /**
     * 是否加入该星球
     */
    private boolean isIn;

    /**
     * 动态：点赞、评论、收藏、转发、分享、浏览数
     */
    private int like;
    private int comment;
    private int collect;
    private int forward;
    private int share;
    private int look;

    /**
     * 动态图片地址
     */
    private List<String> imgUrl;

    /**
     * 链接：地址、图片地址、标题、文字内容
     */
    private String linkUrl;
    private String linkImage;
    private String linkTitle;
    private String linkContent;

    /**
     * 原动态相关
     */
    private boolean hasDeleted;
    private String sDynamicId;
    private String sAuthorId;
    private String sAuthorName;
    private String sStarName;
    private String sStarId;
    private String sText;
    private List<String> sImageUrls;
    private String sLinkUrl;
    private String sLinkImage;
    private String sLinkContent;
    private String sLinkTitle;
    private String sVisibleRange;
    private boolean sHasDelete;

    public String getMyStatus() {
        return myStatus;
    }

    public void setMyStatus(String myStatus) {
        this.myStatus = myStatus;
    }

    public boolean isMyCollection() {
        return myCollection;
    }

    public void setMyCollection(boolean myCollection) {
        this.myCollection = myCollection;
    }

    public boolean isConcernAuthor() {
        return concernAuthor;
    }

    public void setConcernAuthor(boolean concernAuthor) {
        this.concernAuthor = concernAuthor;
    }

    public boolean isHasCollect() {
        return hasCollect;
    }

    public void setHasCollect(boolean hasCollect) {
        this.hasCollect = hasCollect;
    }

    public boolean issHasDelete() {
        return sHasDelete;
    }

    public boolean isHasDelete() {
        return hasDelete;
    }

    public void setHasDelete(boolean hasDelete) {
        this.hasDelete = hasDelete;
    }

    public String getsStarId() {
        return sStarId;
    }

    public void setsStarId(String sStarId) {
        this.sStarId = sStarId;
    }

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDynamicClassify() {
        return dynamicClassify;
    }

    public void setDynamicClassify(int dynamicClassify) {
        this.dynamicClassify = dynamicClassify;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
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

    public int getTriggerAction() {
        return triggerAction;
    }

    public void setTriggerAction(int triggerAction) {
        this.triggerAction = triggerAction;
    }

    public String getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(String triggerId) {
        this.triggerId = triggerId;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public int getVisibleRange() {
        return visibleRange;
    }

    public void setVisibleRange(int visibleRange) {
        this.visibleRange = visibleRange;
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

    public String getImgStarUrl() {
        return imgStarUrl;
    }

    public void setImgStarUrl(String imgStarUrl) {
        this.imgStarUrl = imgStarUrl;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getUpDateTime() {
        return upDateTime;
    }

    public void setUpDateTime(String upDateTime) {
        this.upDateTime = upDateTime;
    }

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public boolean isIn() {
        return isIn;
    }

    public void setIn(boolean in) {
        isIn = in;
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

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getForward() {
        return forward;
    }

    public void setForward(int forward) {
        this.forward = forward;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getLook() {
        return look;
    }

    public void setLook(int look) {
        this.look = look;
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

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getLinkContent() {
        return linkContent;
    }

    public void setLinkContent(String linkContent) {
        this.linkContent = linkContent;
    }

    public boolean isHasDeleted() {
        return hasDeleted;
    }

    public void setHasDeleted(boolean hasDeleted) {
        this.hasDeleted = hasDeleted;
    }

    public String getsDynamicId() {
        return sDynamicId;
    }

    public void setsDynamicId(String sDynamicId) {
        this.sDynamicId = sDynamicId;
    }

    public String getsAuthorId() {
        return sAuthorId;
    }

    public void setsAuthorId(String sAuthorId) {
        this.sAuthorId = sAuthorId;
    }

    public String getsAuthorName() {
        return sAuthorName;
    }

    public void setsAuthorName(String sAuthorName) {
        this.sAuthorName = sAuthorName;
    }

    public String getsStarName() {
        return sStarName;
    }

    public void setsStarName(String sStarName) {
        this.sStarName = sStarName;
    }

    public String getsText() {
        return sText;
    }

    public void setsText(String sText) {
        this.sText = sText;
    }

    public List<String> getsImageUrls() {
        return sImageUrls;
    }

    public void setsImageUrls(List<String> sImageUrls) {
        this.sImageUrls = sImageUrls;
    }

    public String getsLinkUrl() {
        return sLinkUrl;
    }

    public void setsLinkUrl(String sLinkUrl) {
        this.sLinkUrl = sLinkUrl;
    }

    public String getsLinkImage() {
        return sLinkImage;
    }

    public void setsLinkImage(String sLinkImage) {
        this.sLinkImage = sLinkImage;
    }

    public String getsLinkContent() {
        return sLinkContent;
    }

    public void setsLinkContent(String sLinkContent) {
        this.sLinkContent = sLinkContent;
    }

    public String getsLinkTitle() {
        return sLinkTitle;
    }

    public void setsLinkTitle(String sLinkTitle) {
        this.sLinkTitle = sLinkTitle;
    }

    public String getsVisibleRange() {
        return sVisibleRange;
    }

    public void setsVisibleRange(String sVisibleRange) {
        this.sVisibleRange = sVisibleRange;
    }

    public boolean getsHasDelete() {
        return sHasDelete;
    }

    public void setsHasDelete(boolean sHasDelete) {
        this.sHasDelete = sHasDelete;
    }

    @Override
    public String toString() {
        return "DynamicOut{" +
                "dynamicId='" + dynamicId + '\'' +
                ", text='" + text + '\'' +
                ", top=" + top +
                ", type='" + type + '\'' +
                ", dynamicClassify=" + dynamicClassify +
                ", authorId='" + authorId + '\'' +
                ", imgAuthorUrl='" + imgAuthorUrl + '\'' +
                ", authorName='" + authorName + '\'' +
                ", triggerAction=" + triggerAction +
                ", triggerId='" + triggerId + '\'' +
                ", triggerName='" + triggerName + '\'' +
                ", visibleRange=" + visibleRange +
                ", starId='" + starId + '\'' +
                ", starName='" + starName + '\'' +
                ", imgStarUrl='" + imgStarUrl + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", upDateTime='" + upDateTime + '\'' +
                ", issueTime='" + issueTime + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", isIn=" + isIn +
                ", like=" + like +
                ", comment=" + comment +
                ", collect=" + collect +
                ", forward=" + forward +
                ", share=" + share +
                ", look=" + look +
                ", imgUrl=" + imgUrl +
                ", linkUrl='" + linkUrl + '\'' +
                ", linkImage='" + linkImage + '\'' +
                ", linkTitle='" + linkTitle + '\'' +
                ", linkContent='" + linkContent + '\'' +
                ", hasDeleted=" + hasDeleted +
                ", sDynamicId='" + sDynamicId + '\'' +
                ", sAuthorId='" + sAuthorId + '\'' +
                ", sAuthorName='" + sAuthorName + '\'' +
                ", sStarName='" + sStarName + '\'' +
                ", sText='" + sText + '\'' +
                ", sImageUrls='" + sImageUrls + '\'' +
                ", sLinkUrl='" + sLinkUrl + '\'' +
                ", sLinkImage='" + sLinkImage + '\'' +
                ", sLinkContent='" + sLinkContent + '\'' +
                ", sLinkTitle='" + sLinkTitle + '\'' +
                ", sVisibleRange='" + sVisibleRange + '\'' +
                ", sHasDelete='" + sHasDelete + '\'' +
                '}';
    }
}
