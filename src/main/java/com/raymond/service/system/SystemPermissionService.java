package com.raymond.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.system.SystemPermission;

import java.util.List;

/**
 * system_permission: 权限crud操作
 */
public interface SystemPermissionService {
    // 新增权限
    public int addPermission(SystemPermission systemPermission);
    // 修改权限
    public int updatePermission(SystemPermission systemPermission);
    // 删除权限
    public int deletePermissionByPermissionIds(List<Long> permissionIds);
    // 查询所有权限
    public List<SystemPermission> findAllPermission();
    // 分页查询所有权限
    public Page<SystemPermission> findPermissionByPage(SystemPermission systemPermission);
}
