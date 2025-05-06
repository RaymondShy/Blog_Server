package com.raymond.controller.system;

import com.raymond.domain.system.SystemRole;
import com.raymond.service.SystemRoleService;
import com.raymond.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/system/role")
public class SystemRoleController {

    @Autowired
    private SystemRoleService systemRoleService;

    @GetMapping
    public AjaxResult getRoleList() {
        List<SystemRole> roleList = this.systemRoleService.getRoleList();
        return AjaxResult.success(roleList);
    }
}
