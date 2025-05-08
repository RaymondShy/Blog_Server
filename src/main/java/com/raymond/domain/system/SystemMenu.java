package com.raymond.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.raymond.domain.BaseQuery;
import lombok.Builder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
public class SystemMenu extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long menuId;
    private String menuName;
    private String slogan;
    private String menuAbstract;
    private String menuUrl;
    private Integer menuOrderNum;
    private Integer abstractT;
    private String status;
    private Date createTime;
    private Date updateTime;
    @TableField(exist = false)
    private List<SystemCarousel> carouselList;

    @Override
    public String toString() {
        return "SystemMenu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", slogan='" + slogan + '\'' +
                ", menuAbstract='" + menuAbstract + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", menuOrderNum=" + menuOrderNum +
                ", abstractT=" + abstractT +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", carouselList=" + carouselList +
                '}';
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getMenuAbstract() {
        return menuAbstract;
    }

    public void setMenuAbstract(String menuAbstract) {
        this.menuAbstract = menuAbstract;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getMenuOrderNum() {
        return menuOrderNum;
    }

    public void setMenuOrderNum(Integer menuOrderNum) {
        this.menuOrderNum = menuOrderNum;
    }

    public Integer getAbstractT() {
        return abstractT;
    }

    public void setAbstractT(Integer abstractT) {
        this.abstractT = abstractT;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<SystemCarousel> getCarouselList() {
        return carouselList;
    }

    public void setCarouselList(List<SystemCarousel> carouselList) {
        this.carouselList = carouselList;
    }

    public SystemMenu() {
    }

    public SystemMenu(Long menuId, String menuName, String slogan, String menuAbstract, String menuUrl, Integer menuOrderNum, Integer abstractT, String status, Date createTime, Date updateTime, List<SystemCarousel> carouselList) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.slogan = slogan;
        this.menuAbstract = menuAbstract;
        this.menuUrl = menuUrl;
        this.menuOrderNum = menuOrderNum;
        this.abstractT = abstractT;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.carouselList = carouselList;
    }
}
