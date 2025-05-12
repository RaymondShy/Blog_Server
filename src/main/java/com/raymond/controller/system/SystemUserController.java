package com.raymond.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.common.HttpStatus;
import com.raymond.domain.system.SystemUser;
import com.raymond.dto.UserDto;
import com.raymond.service.system.SystemUserService;
import com.raymond.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/system")
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('/api/system/search')")
    public AjaxResult search(UserDto userDto) {
        Page<SystemUser> page = this.systemUserService.search(userDto);
        return AjaxResult.success(page);
    }

    @DeleteMapping("/{idList}")
    public AjaxResult delete(@PathVariable Long[] idList) {
        int row = this.systemUserService.deleteUserById(idList);
        return row > 0 ? AjaxResult.success("Delete user successfully") : AjaxResult.error("Failed to delete user");
    }

    @PostMapping("/{roleId}")
    public AjaxResult add(@RequestBody SystemUser systemUser,@PathVariable Long roleId) {
        SystemUser detail = this.systemUserService.getUserDetail(systemUser);
        if (detail != null) {
            return AjaxResult.error(HttpStatus.CREATE_USER_REPEAT,"User already exist");
        }
        systemUser.setRegisterTime(new Date());
        int row = this.systemUserService.addUser(systemUser,roleId);
        return row >0 ? AjaxResult.success("Add user successfully") : AjaxResult.error("Failed to add user");
    }

    @PutMapping
    public AjaxResult update(@RequestBody SystemUser systemUser) {
        systemUser.setUpdateTime(new Date());
        int row = this.systemUserService.editUser(systemUser);
        return row > 0 ? AjaxResult.success("Update user successfully") : AjaxResult.error("Failed to update user");
    }

}
