package com.raymond.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.SystemUser;
import com.raymond.dto.UserDto;
import com.raymond.service.SystemUserService;
import com.raymond.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system")
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @GetMapping("/search")
    public AjaxResult search(UserDto userDto) {
        Page<SystemUser> page = this.systemUserService.search(userDto);
        return AjaxResult.success(page);
    }

}
