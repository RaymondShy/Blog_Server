package com.raymond.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.SystemUser;
import com.raymond.dto.UserDto;

import java.util.List;

/**
 * 用户接口
 */
public interface SystemUserService {
    public Page<SystemUser> search(UserDto userDto);
}
