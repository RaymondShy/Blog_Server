package com.raymond.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.system.SystemPermission;
import com.raymond.service.system.SystemPermissionService;
import com.raymond.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/system/permission")
public class SystemPermissionController {

    @Autowired
    private SystemPermissionService systemPermissionService;
    private final HttpServletRequest request;

    // 构造函数构造servlet request
    public SystemPermissionController(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @GetMapping("/search")
    public AjaxResult search(SystemPermission systemPermission) {
        Page<SystemPermission> page = this.systemPermissionService.findPermissionByPage(systemPermission);
        return AjaxResult.success(page);
    }

    @GetMapping("/findAll")
    public AjaxResult findAll() {
        List<SystemPermission> list = this.systemPermissionService.findAllPermission();
        return AjaxResult.success(list);
    }

    @PostMapping
    public AjaxResult save(@RequestBody SystemPermission systemPermission) {
        systemPermission.setCreateTime(new Date());
        int row = this.systemPermissionService.addPermission(systemPermission);
        return row > 0 ? AjaxResult.success("ADD permission successfully") : AjaxResult.error("ADD to delete permission");
    }

    @PutMapping
    public AjaxResult update(@RequestBody SystemPermission systemPermission) {
        systemPermission.setUpdateTime(new Date());
        int row = this.systemPermissionService.updatePermission(systemPermission);
        return row > 0 ? AjaxResult.success("UPDATE permission successfully") : AjaxResult.error("UPDATE to delete permission");
    }

    @DeleteMapping("/{idList}")
    public AjaxResult delete(@PathVariable List<Long> idList) {
        int row = this.systemPermissionService.deletePermissionByPermissionIds(idList);
        return row > 0 ? AjaxResult.success("DELETE permission successfully") : AjaxResult.error("DELETE to delete permission");
    }

}
