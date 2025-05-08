package com.raymond.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.system.SystemRole;
import com.raymond.domain.system.SystemRolePermission;
import com.raymond.mapper.system.SystemRoleMapper;
import com.raymond.mapper.system.SystemRolePermissionMapper;
import com.raymond.service.SystemRoleService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
public class SystemRoleServiceImpl implements SystemRoleService {
    @Autowired
    private SystemRoleMapper systemRoleMapper;
    @Autowired
    private SystemRolePermissionMapper systemRolePermissionMapper;
    @Override
    public List<SystemRole> getRoleList() {
        List<SystemRole> list = this.systemRoleMapper.selectList(new LambdaQueryWrapper<>());
        return list;
    }

    /**
     * 分页查询
     * @param systemRole
     * @return
     */
    @Override
    public Page<SystemRole> getRoleListPage(SystemRole systemRole) {
        // 参数校验
        if (systemRole == null) {
            throw new IllegalArgumentException("Role query params cannot be null");
        }
        // 设置默认分页值
        String roleName = Optional.ofNullable(systemRole.getRoleName()).orElse("");
        String description = Optional.ofNullable(systemRole.getDescription()).orElse("");
        // 构建分页对象
        Page<SystemRole> page = new Page<>(systemRole.getPageNum(), systemRole.getPageSize());
        // 构建查询条件
        LambdaQueryWrapper<SystemRole> wrapper = new LambdaQueryWrapper<>();
        // 排序条件
        if (StringUtils.isNotBlank(systemRole.getRoleCode())) {
            wrapper.orderByDesc(SystemRole::getRoleCode);
        }
        wrapper.orderByAsc(SystemRole::getCreateTime);

        // 查询条件
        wrapper.like(SystemRole::getRoleName, roleName)
                .like(SystemRole::getDescription, description);

        return systemRoleMapper.selectPage(page, wrapper);
    }

    /**
     * 新增角色以及分配权限
     * @param systemRole
     * @param permissionIds
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addRole(SystemRole systemRole, Long[] permissionIds) {
        // 参数校验
        if (systemRole == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }

        int row = systemRoleMapper.insert(systemRole);
        if (row == 0) {
            log.warn("Failed to insert role: {}", systemRole);
            return row;
        }

        if (permissionIds != null && permissionIds.length > 0) {
            // 批量插入权限
            List<SystemRolePermission> rolePermissions = this.batchFunctionSystemRolePermission(systemRole, permissionIds);
            systemRolePermissionMapper.batchInsert(rolePermissions);
            log.debug("Added {} permissions for role {}", permissionIds.length, systemRole.getRoleId());
        }

        return row;
    }

    /**
     * 修改角色
     * @param systemRole
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateRole(SystemRole systemRole, Long[] permissionIds) {
        // 参数校验
        if (systemRole == null || systemRole.getRoleId() == null) {
            throw new IllegalArgumentException("Role and roleId cannot be null");
        }

        // 更新角色基本信息
        int row = systemRoleMapper.updateById(systemRole);
        if (row == 0) {
            log.warn("Failed to update role, roleId: {}", systemRole.getRoleId());
            throw new IllegalArgumentException("角色更新失败");
        }

        // 处理权限变更
        handleRolePermissions(systemRole, permissionIds);

        return row;
    }

    @Override
    public int deleteRoleByIds(Long[] roleIds) {
        if (roleIds == null || roleIds.length == 0) {
            throw new IllegalArgumentException("RoleIds cannot be null");
        }
        // 删除中间表数据
        Arrays.stream(roleIds).forEach(roleId -> {
            // 删除旧权限
            this.deleteOldPermission(roleId);
        });
        return this.systemRolePermissionMapper.deleteBatchIds(Arrays.asList(roleIds));
    }

    @Override
    public Long[] getPermissionListByRoleId(Long roleId) {
        // 参数校验
        if (roleId == null) {
            throw new IllegalArgumentException("Role ID cannot be null");
        }

        // 查询并直接映射为权限ID数组
        return systemRolePermissionMapper.selectList(
                        new LambdaQueryWrapper<SystemRolePermission>()
                                .select(SystemRolePermission::getPermissionId)
                                .eq(SystemRolePermission::getRoleId, roleId))
                .stream()
                .map(SystemRolePermission::getPermissionId)
                .toArray(Long[]::new);
    }

    @Override
    public int addRolePermission(Long roleId, Long permissionId) {
        return this.systemRolePermissionMapper.insert(SystemRolePermission
                .builder().roleId(roleId).permissionId(permissionId).createTime(new Date()).build());
    }

    /**
     * 批量操作方法
     * @param systemRole
     * @param permissionIds
     * @return
     */
    public List<SystemRolePermission> batchFunctionSystemRolePermission(SystemRole systemRole,Long[] permissionIds) {
        List<SystemRolePermission> rolePermissions = Arrays.stream(permissionIds)
                .map(permissionId -> {
                    SystemRolePermission rp = SystemRolePermission.builder().roleId(systemRole.getRoleId()).permissionId(permissionId).createTime(new Date()).build();
                    return rp;
                })
                .collect(Collectors.toList());
        return rolePermissions;
    }
    /**
     * 删除旧权限
     * @param roleId
     * @return
     */
    private int deleteOldPermission(Long roleId){
        return systemRolePermissionMapper.delete(
                new QueryWrapper<SystemRolePermission>()
                        .eq(SystemRolePermission.SYSTEM_ROLE_ID, roleId));
    }

    /**
     * 处理权限变更
     * @param systemRole
     * @param permissionIds
     */
    private void handleRolePermissions(SystemRole systemRole,Long[] permissionIds) {
        // 删除旧权限
        this.deleteOldPermission(systemRole.getRoleId());
        // 如果有新权限则添加
        if (permissionIds != null && permissionIds.length > 0) {
            List<SystemRolePermission> rolePermissions = batchFunctionSystemRolePermission(systemRole, permissionIds);

            systemRolePermissionMapper.batchInsert(rolePermissions);
        }
    }

}
