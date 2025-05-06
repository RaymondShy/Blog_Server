package com.raymond.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.raymond.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
    system_role: 用户角色表
*/
public class SystemRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long roleId;
    private String roleName;
    private String roleCode;
    private String description;
    private Date createTime;
    private Date updateTime;

    @TableField(exist = false)
    private List<SystemPermission> permissionList;

    @Override
    public String toString() {
        return "SystemRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", permissionList=" + permissionList +
                '}';
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<SystemPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SystemPermission> permissionList) {
        this.permissionList = permissionList;
    }

    public SystemRole() {
    }

    public SystemRole(Long roleId, String roleName, String roleCode, String description, Date createTime, Date updateTime, List<SystemPermission> permissionList) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleCode = roleCode;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.permissionList = permissionList;
    }
}
