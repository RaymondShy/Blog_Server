package com.raymond.domain.system;

import java.io.Serializable;
import java.util.Date;

public class SystemMenuCarousel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long menuId;
    private Long carouselId;
    private Date createTime;

    @Override
    public String toString() {
        return "SystemMenuCarousel{" +
                "id=" + id +
                ", menuId=" + menuId +
                ", carouselId=" + carouselId +
                ", createTime=" + createTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(Long carouselId) {
        this.carouselId = carouselId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SystemMenuCarousel() {
    }

    public SystemMenuCarousel(Long id, Long menuId, Long carouselId, Date createTime) {
        this.id = id;
        this.menuId = menuId;
        this.carouselId = carouselId;
        this.createTime = createTime;
    }
}
