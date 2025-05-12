package com.raymond.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.system.SystemRole;

import java.util.List;

/**
 * 角色CRUD操作
 */
public interface SystemRoleService {
    // 查询所有角色
    public List<SystemRole> getRoleList();
    // 分页查所有角色
    public Page<SystemRole> getRoleListPage(SystemRole systemRole);
    // 新增角色
    public int addRole(SystemRole systemRole, Long[] permissionIds);
    // 修改角色
    public int updateRole(SystemRole systemRole, Long[] permissionIds);
    // 删除/批量删除角色
    public int deleteRoleByIds(Long[] roleIds);
    // 根据角色ID查询角色对应的权限
    public Long[] getPermissionListByRoleId(Long roleId);
    // 给角色分配权限
    public int addRolePermission(Long roleId,Long permissionId);
}
