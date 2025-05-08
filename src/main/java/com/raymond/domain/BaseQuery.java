package com.raymond.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

public class BaseQuery {
    // 当前页
    @TableField(exist = false)
    private Integer pageNum = 1;

    // 每页条数
    @TableField(exist = false)
    private Integer pageSize = 10;

    // 排序字段（可选）
    @TableField(exist = false)
    private String sortField;

    @TableField(exist = false)
    private String key;

    // 排序方向 asc / desc（可选）
    @TableField(exist = false)
    private String sortOrder;

    @Override
    public String toString() {
        return "BaseQuery{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", sortField='" + sortField + '\'' +
                ", key='" + key + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                '}';
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public BaseQuery() {
    }

    public BaseQuery(Integer pageNum, Integer pageSize, String sortField, String key, String sortOrder) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.sortField = sortField;
        this.key = key;
        this.sortOrder = sortOrder;
    }
}
