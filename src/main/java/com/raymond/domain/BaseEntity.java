package com.raymond.domain;

import com.baomidou.mybatisplus.annotation.TableField;

public class BaseEntity {
    @com.fasterxml.jackson.annotation.JsonIgnore // 序列化JSON时不反悔该字段
    @TableField(exist = false,select = false)
    private int pageNum = 1;
    @com.fasterxml.jackson.annotation.JsonIgnore
    @TableField(exist = false,select = false)
    private int pageSize = 10;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }

    public BaseEntity(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public BaseEntity() {
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
