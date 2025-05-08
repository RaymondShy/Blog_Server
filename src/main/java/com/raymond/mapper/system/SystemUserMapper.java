package com.raymond.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.raymond.domain.system.SystemPermission;
import com.raymond.domain.system.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUser> {
    // 根据用户名查询用户权限
    public List<SystemPermission> getUserAllPermissionByUsername(String username);
    // 新增中间表数据
    public int addUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId, @Param("createTime") Date createTime);
}
