package com.yh.jiran.module.dynamic.model.entity;

/**
 * @author: 闫昊
 * @date: 2018/8/6
 * @function: 动态信息流样式
 * todo 删除
 */
public enum DynamicType {
    /**
     * 纯文字
     */
    TEXT,

    /**
     * 单图
     */
    SINGLE_PIC,

    /**
     * 多图
     */
    MULTI_PIC,

    /**
     * 链接1
     */
    LINK,

    /**
     * 链接2
     */
    LINK_RAW,

    /**
     * 点赞、收藏
     */
    LIKE_AND_COLLECT,

    /**
     * 转发-删除
     */
    FORWARD_DELETE,

    /**
     * 转发-多图
     */
    FORWARD_MULTI_PIC,

    /**
     * 转发-链接
     */
    FORWARD_LINK,

    /**
     * 转发-单图
     */
    FORWARD_SINGLE_PIC
}
