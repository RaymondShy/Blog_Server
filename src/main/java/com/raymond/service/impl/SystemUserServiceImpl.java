package com.raymond.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.system.SystemPermission;
import com.raymond.domain.system.SystemUser;
import com.raymond.domain.UserTest;
import com.raymond.dto.UserDto;
import com.raymond.mapper.SystemUserMapper;
import com.raymond.service.SystemUserService;
import com.raymond.utils.IpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Transactional(rollbackFor = Exception.class) // 添加事务回滚
    @Override
    public int deleteUserById(Long[] idList) {
        if (idList == null || idList.length == 0) {
            return 0;
        }
        LambdaQueryWrapper<SystemUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SystemUser::getUserId, idList);
        return this.systemUserMapper.delete(lambdaQueryWrapper);
    }

    /**
     * 创建用户时，用户名、昵称、邮箱、手机号不能重复
     * @param systemUser
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(SystemUser systemUser) {
        String ip = IpUtils.getIpAddr(request); // 登录IP
        systemUser.setLoginIp(ip);
        systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        int row = this.systemUserMapper.insert(systemUser);
        return row;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int editUser(SystemUser systemUser) {
        return this.systemUserMapper.updateById(systemUser);
    }

    @Override
    public SystemUser getUserByUsername(String username) {
        LambdaQueryWrapper<SystemUser> lambdaQueryWrapper = new LambdaQueryWrapper<SystemUser>().eq(SystemUser::getUserName, username);
        SystemUser systemUser = this.systemUserMapper.selectOne(lambdaQueryWrapper);
        return systemUser;
    }

    @Override
    public List<SystemPermission> getUserPermissionByUsername(String username) {
        return this.systemUserMapper.getUserAllPermissionByUsername(username);
    }

    @Override
    public SystemUser getUserDetail(SystemUser systemUser) {
        LambdaQueryWrapper<SystemUser> lambdaQueryWrapper = new LambdaQueryWrapper<SystemUser>();
        lambdaQueryWrapper.eq(SystemUser::getUserName, systemUser.getUserName())
                .or()
                .eq(SystemUser::getNickName, systemUser.getNickName())
                .or()
                .eq(SystemUser::getEmail, systemUser.getEmail())
                .or()
                .eq(SystemUser::getTel, systemUser.getTel());
        SystemUser user = this.systemUserMapper.selectOne(lambdaQueryWrapper);
        return user;
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
