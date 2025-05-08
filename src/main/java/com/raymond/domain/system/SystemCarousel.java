package com.raymond.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.raymond.domain.BaseEntity;
import lombok.Builder;

import java.io.Serializable;
import java.util.Date;

@Builder
public class SystemCarousel extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long carouselId;
    private Long menuId;
    private String imageUrl;
    private String carouselTitle;
    private String linkUrl;
    private Integer sortOrder;
    private String status;
    private Date startTime;
    private Date endTime;
    private Integer carouselTime;
    private Date createTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "SystemCarousel{" +
                "carouselId=" + carouselId +
                ", menuId=" + menuId +
                ", imageUrl='" + imageUrl + '\'' +
                ", carouselTitle='" + carouselTitle + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", sortOrder=" + sortOrder +
                ", status='" + status + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", carouselTime=" + carouselTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(Long carouselId) {
        this.carouselId = carouselId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCarouselTitle() {
        return carouselTitle;
    }

    public void setCarouselTitle(String carouselTitle) {
        this.carouselTitle = carouselTitle;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCarouselTime() {
        return carouselTime;
    }

    public void setCarouselTime(Integer carouselTime) {
        this.carouselTime = carouselTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public SystemCarousel() {
    }

    public SystemCarousel(Long carouselId, Long menuId, String imageUrl, String carouselTitle, String linkUrl, Integer sortOrder, String status, Date startTime, Date endTime, Integer carouselTime, Date createTime, Date updateTime) {
        this.carouselId = carouselId;
        this.menuId = menuId;
        this.imageUrl = imageUrl;
        this.carouselTitle = carouselTitle;
        this.linkUrl = linkUrl;
        this.sortOrder = sortOrder;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.carouselTime = carouselTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
