package com.raymond.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.raymond.domain.system.SystemRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SystemRolePermissionMapper extends BaseMapper<SystemRolePermission> {
    // SystemRolePermissionMapper.java
    public int batchInsert(@Param("list") List<SystemRolePermission> rolePermissions);
}
