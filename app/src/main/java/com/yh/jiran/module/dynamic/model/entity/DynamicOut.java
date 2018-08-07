package com.yh.jiran.module.dynamic.model.entity;

import com.yh.jiran.module.dynamic.model.DynamicType;

/**
 * @author: 闫昊
 * @date: 2018/8/7
 * @function: 星球外动态
 */
public class DynamicOut {

    /**
     * 纯文字
     */
    public static final int TEXT = 1;

    /**
     * 单图
     */
    public static final int SINGLE_PIC = 2;

    /**
     * 多图
     */
    public static final int MULTI_PIC = 3;

    /**
     * 链接1
     */
    public static final int LINK = 4;

    /**
     * 链接2
     */
    public static final int LINK_RAW = 5;

    /**
     * 点赞、收藏
     */
    public static final int LIKE_AND_COLLECT = 6;

    /**
     * 转发-删除
     */
    public static final int FORWARD_DELETE = 7;

    /**
     * 转发-多图
     */
    public static final int FORWARD_MULTI_PIC = 8;

    /**
     * 转发-链接
     */
    public static final int FORWARD_LINK = 9;

    /**
     * 转发-单图
     */
    public static final int FORWARD_SINGLE_PIC = 10;

    public int type;
    private String imgStarUrl;
    private String starName;
    private boolean isIn;
    private String text;
    private String imgAuthorUrl;
    private String authorName;
    private int like;
    private int comment;
}
