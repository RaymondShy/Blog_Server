package com.raymond.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.raymond.domain.system.SystemCarousel;
import com.raymond.domain.system.SystemMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * system_menu：菜单表
 */
@Mapper
public interface SystemMenuMapper extends BaseMapper<SystemMenu> {
    // 查询菜单所对应的图片
    List<SystemCarousel> findByMenuId(Long menuId);
}
