package com.raymond.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.raymond.domain.system.SystemPermission;
import com.raymond.domain.system.SystemUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUser> {
    // 根据用户名查询用户权限
    public List<SystemPermission> getUserAllPermissionByUsername(String username);
}
