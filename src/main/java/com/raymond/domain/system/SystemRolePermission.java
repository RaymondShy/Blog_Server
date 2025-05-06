package com.raymond.domain.system;

import lombok.Builder;

import java.util.Date;

@Builder
public class SystemRolePermission {
    private Long id;
    private Long roleId;
    private Long permissionId;
    private Date createTime;

    public static final String SYSTEM_ROLE_ID = "role_id";
    public static final String PERMISSION_ID = "permission_id";
    public static final String CREATE_TIME = "create_time";

    @Override
    public String toString() {
        return "SystemRolePermission{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                ", createTime=" + createTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SystemRolePermission() {
    }

    public SystemRolePermission(Long id, Long roleId, Long permissionId, Date createTime) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.createTime = createTime;
    }
}
