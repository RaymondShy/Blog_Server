package com.raymond.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.raymond.domain.system.SystemCarousel;
import com.raymond.domain.system.SystemMenuCarousel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemMenuCarouselMapper extends BaseMapper<SystemMenuCarousel> {
    void insertBatch(List<SystemMenuCarousel> menuCarouselList);

}
