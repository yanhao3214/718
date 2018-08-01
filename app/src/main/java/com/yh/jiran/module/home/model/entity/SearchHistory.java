package com.yh.jiran.module.home.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author: 闫昊
 * @date: 2018/7/30
 * @function: 首页星球-搜索记录
 */
@Entity(nameInDb = "search_history")
public class SearchHistory {

    @Id(autoincrement = true)
    private Long historyId;

    private String content;

    @Generated(hash = 1326058489)
    public SearchHistory(Long historyId, String content) {
        this.historyId = historyId;
        this.content = content;
    }

    @Generated(hash = 1905904755)
    public SearchHistory() {
    }

    public Long getHistoryId() {
        return this.historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
