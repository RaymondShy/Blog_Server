package com.raymond.service.impl.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.system.SystemPermission;
import com.raymond.domain.system.SystemRolePermission;
import com.raymond.mapper.system.SystemPermissionMapper;
import com.raymond.mapper.system.SystemRolePermissionMapper;
import com.raymond.service.system.SystemPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * system_permission: crud操作实现类
 */
@Service
public class SystemPermissionServiceImpl implements SystemPermissionService {

    @Autowired
    private SystemPermissionMapper systemPermissionMapper;
    @Autowired
    private SystemRolePermissionMapper systemRolePermissionMapper;

    @Override
    public int addPermission(SystemPermission systemPermission) {
        return this.systemPermissionMapper.insert(systemPermission);
    }

    @Override
    public int updatePermission(SystemPermission systemPermission) {
        return this.systemPermissionMapper.updateById(systemPermission);
    }

    @Override
    public int deletePermissionByPermissionIds(List<Long> permissionIds) {
        // 删除中间表数据
        this.systemRolePermissionMapper.delete(new QueryWrapper<SystemRolePermission>()
                .in(SystemRolePermission.PERMISSION_ID,permissionIds));
        // 删除主表数据
        return this.systemPermissionMapper.deleteBatchIds(permissionIds);
    }

    @Override
    public List<SystemPermission> findAllPermission() {
        return this.systemPermissionMapper.selectList(new LambdaQueryWrapper<SystemPermission>());
    }

    @Override
    public Page<SystemPermission> findPermissionByPage(SystemPermission systemPermission) {
        if (systemPermission == null) {
            throw new IllegalArgumentException("SystemPermission query params cannot be null");
        }

        // 创建分页对象
        Page<SystemPermission> page = new Page<>(systemPermission.getPageNum(), systemPermission.getPageSize());

        // 创建LambdaQueryWrapper
        LambdaQueryWrapper<SystemPermission> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 通过方法链构建查询条件，避免多个if判断
        Optional.ofNullable(systemPermission.getPermName())
                .filter(StringUtils::isNotBlank) // 只有非空时才拼接条件
                .ifPresent(permName -> lambdaQueryWrapper.like(SystemPermission::getPermName, permName));

        Optional.ofNullable(systemPermission.getMethod())
                .filter(StringUtils::isNotBlank)
                .ifPresent(method -> lambdaQueryWrapper.like(SystemPermission::getMethod, method));

        // 添加排序条件
        Optional.ofNullable(systemPermission.getOrderNum())
                .ifPresent(orderNum -> lambdaQueryWrapper.orderByAsc(SystemPermission::getOrderNum));

        // 默认按创建时间降序
        lambdaQueryWrapper.orderByDesc(SystemPermission::getCreateTime);

        // 执行查询
        return systemPermissionMapper.selectPage(page, lambdaQueryWrapper);
    }


}
