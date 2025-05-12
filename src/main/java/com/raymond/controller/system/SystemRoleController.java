package com.raymond.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.system.SystemRole;
import com.raymond.service.system.SystemRoleService;
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

    @PostMapping("/{idList}")
    public AjaxResult addRole(@RequestBody SystemRole systemRole,@PathVariable Long[] idList) {
        int row = this.systemRoleService.addRole(systemRole,idList);
        return row > 0 ? AjaxResult.success("ADD user successfully") : AjaxResult.error("ADD to delete user");
    }

    @PutMapping("/{idList}")
    public AjaxResult updateRole(@RequestBody SystemRole systemRole,@PathVariable Long[] idList) {
        int row = this.systemRoleService.updateRole(systemRole,idList);
        return row > 0 ? AjaxResult.success("Update role successfully") : AjaxResult.error("Failed to update role");
    }

    @DeleteMapping("/{idList}")
    public AjaxResult deleteRole(@PathVariable List<Long> idList) {
        int row = this.systemRoleService.deleteRoleByIds(idList.toArray(new Long[0]));
        return row > 0 ? AjaxResult.success("Delete user successfully") : AjaxResult.error("Failed to delete user");
    }

}
