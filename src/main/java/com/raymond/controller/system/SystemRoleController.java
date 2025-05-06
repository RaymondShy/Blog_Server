package com.raymond.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.system.SystemRole;
import com.raymond.service.SystemRoleService;
import com.raymond.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search")
    public AjaxResult getRoleListBySearch(SystemRole systemRole) {
        Page<SystemRole> roleListPage = this.systemRoleService.getRoleListPage(systemRole);
        return AjaxResult.success(roleListPage);
    }

    @PostMapping
    public AjaxResult addRole(SystemRole systemRole,Long[] permissionIds) {
        int row = this.systemRoleService.addRole(systemRole,permissionIds);
        return row > 0 ? AjaxResult.success("Delete user successfully") : AjaxResult.error("Failed to delete user");
    }

    @PutMapping
    public AjaxResult updateRole(SystemRole systemRole,Long[] permissionIds) {
        int row = this.systemRoleService.updateRole(systemRole,permissionIds);
        return row > 0 ? AjaxResult.success("Update user successfully") : AjaxResult.error("Failed to update user");
    }

    @DeleteMapping("/{idList}")
    public AjaxResult deleteRole(@PathVariable List<Long> idList) {
        int row = this.systemRoleService.deleteRoleByIds(idList.toArray(new Long[0]));
        return row > 0 ? AjaxResult.success("Delete user successfully") : AjaxResult.error("Failed to delete user");
    }

}
