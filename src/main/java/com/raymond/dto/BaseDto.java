package com.raymond.dto;

public class BaseDto {
    private int pageNum = 1; // 第几页
    private int pageSize = 10; // 每页显示的条数
    private String sort = "createTime"; // 排序字段
    private String key; // 搜索关键词

    @Override
    public String toString() {
        return "BaseDto{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", sort='" + sort + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    public BaseDto() {
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BaseDto(int pageNum, int pageSize, String sort, String key) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.sort = sort;
        this.key = key;
    }
}
