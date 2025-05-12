package com.raymond.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.system.SystemMenu;
import com.raymond.service.system.SystemMenuService;
import com.raymond.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @ApiOperation("新增菜单")
    @PostMapping("/{idList}")
    public AjaxResult save(@RequestBody SystemMenu systemMenu, @PathVariable Long[] idList) {
        systemMenu.setCreateTime(new Date());
        Long menuId = this.systemMenuService.add(systemMenu,idList);
        return AjaxResult.success("新增菜单成功",menuId);
    }

    @ApiOperation("删除菜单及中间表数据")
    @DeleteMapping("/{menuId}")
    public AjaxResult delete(@PathVariable Long menuId) {
        int row = this.systemMenuService.delete(menuId);
        return row > 0 ? AjaxResult.success("delete success") : AjaxResult.error("delete fail");
    }

    @ApiOperation("修改菜单")
    @PutMapping("/{idList}")
    public AjaxResult update(@RequestBody SystemMenu systemMenu,@PathVariable Long[] idList) {
        systemMenu.setUpdateTime(new Date());
        int row = this.systemMenuService.update(systemMenu, idList);
        return row > 0 ? AjaxResult.success("update success") : AjaxResult.error("update fail");
    }

    @ApiOperation("根据ID查询菜单")
    @GetMapping("/{menuId}")
    public AjaxResult findById(@PathVariable Long menuId) {
        SystemMenu systemMenu = this.systemMenuService.findByMenuId(menuId);
        return AjaxResult.success(systemMenu);
    }
}
