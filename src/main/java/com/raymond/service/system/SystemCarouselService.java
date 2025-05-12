package com.raymond.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.system.SystemCarousel;

import java.util.List;

public interface SystemCarouselService {
    // 分页查询轮播图
    public Page<SystemCarousel> findByMenuId(SystemCarousel systemCarousel);
    // 新增轮播图
    public int add(SystemCarousel systemCarousel);
    // 修改轮播图
    public int update(SystemCarousel systemCarousel);
    // 删除轮播图
    public int delete(Long menuId);
    // 根据id查询轮播图
    public SystemCarousel findById(Long carouselId);
    // 查询图片名称列表
    public List<SystemCarousel> findAll();
}
