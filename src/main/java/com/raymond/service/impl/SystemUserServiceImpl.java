package com.raymond.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.SystemUser;
import com.raymond.dto.UserDto;
import com.raymond.mapper.SystemUserMapper;
import com.raymond.service.SystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Override
    public Page<SystemUser> search(UserDto userDto) {
        Page<SystemUser> page = this.systemUserMapper.selectPage(
                new Page<SystemUser>(userDto.getPageNum(), userDto.getPageSize()),
                Wrappers.<SystemUser>lambdaQuery()
                        .like(StringUtils.isNotBlank(userDto.getKey()), SystemUser::getUserName, userDto.getKey())
                        .or()
                        .like(StringUtils.isNotBlank(userDto.getKey()), SystemUser::getNickName, userDto.getKey())
                        .orderBy(StringUtils.isNotBlank(userDto.getSort()),
                                true,
                                getSortField(userDto.getSort()))
        );
        return page;
    }

    // 安全获取排序字段方法
    private SFunction<SystemUser, ?> getSortField(String fieldName) {
        switch(fieldName) {
            case "createTime": return SystemUser::getCreateTime;
            case "updateTime": return SystemUser::getUpdateTime;
            case "username": return SystemUser::getUserName;
            default: return SystemUser::getUserId;
        }
    }
}
