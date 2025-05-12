package com.raymond.service.impl.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.common.ErrorCode;
import com.raymond.domain.system.SystemCarousel;
import com.raymond.factory.PageFactory;
import com.raymond.mapper.system.SystemCarouselMapper;
import com.raymond.mapper.system.SystemMenuCarouselMapper;
import com.raymond.service.system.SystemCarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class SystemCarouselServiceImpl implements SystemCarouselService {
    @Autowired
    private SystemCarouselMapper systemCarouselMapper;
    @Autowired
    private SystemMenuCarouselMapper systemMenuCarouselMapper;

    @Override
    public Page<SystemCarousel> findByMenuId(SystemCarousel systemCarousel) {
        // 参数校验
        Objects.requireNonNull(systemCarousel, ErrorCode.PARAMETER_ERROR.getMessage());
        // 构造分页对象和查询条件
        Page<?> page =  PageFactory.defaultPage(systemCarousel);
        LambdaQueryWrapper<SystemCarousel> wrapper = new LambdaQueryWrapper<>();
        // 使用Stream API处理查询条件
        Stream.of(
                new SystemMenuServiceImpl.QueryCondition<>(systemCarousel.getKey(),
                        name -> wrapper.like(SystemCarousel::getCarouselTitle, name))
        ).forEach(SystemMenuServiceImpl.QueryCondition::applyIfPresent);

        return this.systemCarouselMapper.selectPage((Page<SystemCarousel>) page, wrapper);
    }

    @Override
    public int add(SystemCarousel systemCarousel) {
        return 0;
    }

    @Override
    public int update(SystemCarousel systemCarousel) {
        return 0;
    }

    @Override
    public int delete(Long menuId) {
        return 0;
    }

    @Override
    public SystemCarousel findById(Long carouselId) {
        return null;
    }

    /**
     * 查询图片名称列表
     * @return
     */
    @Override
    public List<SystemCarousel> findAll() {
        LambdaQueryWrapper<SystemCarousel> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SystemCarousel::getCarouselId, SystemCarousel::getCarouselTitle,SystemCarousel::getImageUrl);
        return this.systemCarouselMapper.selectList(wrapper);
    }
}
