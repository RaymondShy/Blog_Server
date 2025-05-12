package com.raymond.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.system.SystemPermission;
import com.raymond.domain.system.SystemUser;
import com.raymond.domain.UserTest;
import com.raymond.dto.UserDto;

import java.util.List;

/**
 * 用户接口
 */
public interface SystemUserService {
    public Page<SystemUser> search(UserDto userDto);
    // TODO 测试登录 后续要接入Security
    public int login(UserTest userTest);
    // 删除用户
    public int deleteUserById(Long[] idList);
    // TODO 新增管理员后续要修改
    public int addUser(SystemUser systemUser,Long roleId);
    // TODO 修改管理员后续要修改
    public int editUser(SystemUser systemUser);
    // 根据用户名查询用户
    public SystemUser getUserByUsername(String username);
    // 根据用户名查询用户权限
    public List<SystemPermission> getUserPermissionByUsername(String username);
    // 新增用户时查询用户名、昵称、邮箱、电话号是否重复
    public SystemUser getUserDetail(SystemUser systemUser);
}
