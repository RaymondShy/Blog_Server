package com.raymond.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.SystemUser;
import com.raymond.domain.UserTest;
import com.raymond.dto.UserDto;
import com.raymond.mapper.SystemUserMapper;
import com.raymond.service.SystemUserService;
import com.raymond.utils.IpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private HttpServletRequest request;

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

    @Override
    public int login(UserTest userTest) {
        QueryWrapper<SystemUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userTest.getUserName());
        queryWrapper.eq("password", userTest.getPassword());
        SystemUser systemUser = this.systemUserMapper.selectOne(queryWrapper);
        if (systemUser == null) {
            return 0;
        }
        // 更新用户最后登录时间和最后登录IP
        LambdaUpdateWrapper<SystemUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SystemUser::getUserName, userTest.getUserName())
                .set(SystemUser::getLastLoginIp, IpUtils.getIpAddr(request))
                .set(SystemUser::getLastLoginTime,new Date());
        this.systemUserMapper.update(systemUser, updateWrapper);
        return 1;
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
