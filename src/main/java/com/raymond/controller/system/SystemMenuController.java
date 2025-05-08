package com.raymond.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.system.SystemMenu;
import com.raymond.service.SystemMenuService;
import com.raymond.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/system/menu")
@Api(tags = "菜单管理")
public class SystemMenuController {
    @Autowired
    private SystemMenuService systemMenuService;

    @ApiOperation("分页查询菜单")
    @GetMapping("/search")
    public AjaxResult search(SystemMenu systemMenu) {
        Page<SystemMenu> page = this.systemMenuService.findPage(systemMenu);
        return AjaxResult.success(page);
    }
}
