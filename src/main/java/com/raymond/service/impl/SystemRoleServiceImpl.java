package com.raymond.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.raymond.domain.system.SystemRole;
import com.raymond.mapper.SystemRoleMapper;
import com.raymond.service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemRoleServiceImpl implements SystemRoleService {
    @Autowired
    private SystemRoleMapper systemRoleMapper;
    @Override
    public List<SystemRole> getRoleList() {
        List<SystemRole> list = this.systemRoleMapper.selectList(new LambdaQueryWrapper<>());
        return list;
    }
}
