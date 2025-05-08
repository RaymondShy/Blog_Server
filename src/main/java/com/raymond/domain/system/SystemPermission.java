package com.raymond.domain.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.raymond.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * system_permission: 系统权限表
 */
public class SystemPermission extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long permissionId;
    private String permName;
    private String permCode;
    private String permType;
    private String url;
    private String method;
    private Long parentId;
    private Integer orderNum;
    private String icon;
    private Date createTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "SystemPermission{" +
                "permissionId=" + permissionId +
                ", permName='" + permName + '\'' +
                ", permCode='" + permCode + '\'' +
                ", permType='" + permType + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", parentId=" + parentId +
                ", orderNum=" + orderNum +
                ", icon='" + icon + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getPermCode() {
        return permCode;
    }

    public void setPermCode(String permCode) {
        this.permCode = permCode;
    }

    public String getPermType() {
        return permType;
    }

    public void setPermType(String permType) {
        this.permType = permType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public SystemPermission() {
    }

    public SystemPermission(Long permissionId, String permName, String permCode, String permType, String url, String method, Long parentId, Integer orderNum, String icon, Date createTime, Date updateTime) {
        this.permissionId = permissionId;
        this.permName = permName;
        this.permCode = permCode;
        this.permType = permType;
        this.url = url;
        this.method = method;
        this.parentId = parentId;
        this.orderNum = orderNum;
        this.icon = icon;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
