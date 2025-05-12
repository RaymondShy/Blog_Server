package com.raymond.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.system.SystemMenu;

public interface SystemMenuService {
    // 分页查询菜单
    public Page<SystemMenu> findPage(SystemMenu systemMenu);
    // 新增菜单
    public Long add(SystemMenu systemMenu,Long[] carouselIds);
    // 修改菜单
    public int update(SystemMenu systemMenu,Long[] carouselIds);
    // 删除菜单
    public int delete(Long menuId);
    // 根据id查询菜单
    public SystemMenu findByMenuId(Long menuId);
}
