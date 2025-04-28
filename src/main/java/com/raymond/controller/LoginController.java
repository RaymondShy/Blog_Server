package com.raymond.controller;

import com.raymond.domain.UserTest;
import com.raymond.service.SystemUserService;
import com.raymond.utils.AjaxResult;
import com.raymond.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private SystemUserService systemUserService;

    @PostMapping("/pwd")
    public AjaxResult loginPwd(@RequestBody UserTest user) {
        int row = this.systemUserService.login(user);
        // TODO 模拟数据库操作
        if (row == 1){
            String token = JWTUtil.getToken(user.getUserName());
            return AjaxResult.success("登录成功",token);
        }else{
            return AjaxResult.error("用户名或密码错误");
        }
    }
}
