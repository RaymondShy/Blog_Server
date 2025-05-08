package com.raymond.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.common.ErrorCode;
import com.raymond.domain.system.SystemCarousel;
import com.raymond.domain.system.SystemMenu;
import com.raymond.domain.system.SystemMenuCarousel;
import com.raymond.exception.BusinessException;
import com.raymond.factory.PageFactory;
import com.raymond.mapper.system.SystemCarouselMapper;
import com.raymond.mapper.system.SystemMenuCarouselMapper;
import com.raymond.mapper.system.SystemMenuMapper;
import com.raymond.service.SystemCarouselService;
import com.raymond.service.SystemMenuService;
import com.raymond.utils.LambdaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Log4j2
@Service
public class SystemMenuServiceImpl implements SystemMenuService {

    @Autowired
    private SystemMenuMapper systemMenuMapper;
    @Autowired
    private SystemMenuCarouselMapper systemMenuCarouselMapper;

    @Override
    public Page<SystemMenu> findPage(SystemMenu systemMenu) {
        // 参数校验
        Objects.requireNonNull(systemMenu, ErrorCode.PARAMETER_ERROR.getMessage());

        // 创建分页对象和查询条件
        Page<?> page = PageFactory.defaultPage(systemMenu);
        LambdaQueryWrapper<SystemMenu> wrapper = new LambdaQueryWrapper<>();

        // 使用Stream API处理查询条件
        Stream.of(
                new QueryCondition<>(systemMenu.getKey(),
                        name -> wrapper.like(SystemMenu::getMenuName, name)),
                new QueryCondition<>(systemMenu.getMenuUrl(),
                        url -> wrapper.eq(SystemMenu::getMenuUrl, url))
        ).forEach(QueryCondition::applyIfPresent);

        // 处理排序
        handleSorting(systemMenu, wrapper);
        Page<SystemMenu> menuPage = systemMenuMapper.selectPage((Page<SystemMenu>) page, wrapper);
        menuPage.getRecords().forEach(record ->
                record.setCarouselList(
                        Optional.ofNullable(systemMenuMapper.findByMenuId(record.getMenuId()))
                                .orElseGet(ArrayList::new)
                )
        );
        return menuPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(SystemMenu systemMenu, Long[] carouselIds) {
        // 参数校验
        validateInput(systemMenu, carouselIds);

        try {
            // 1. 插入菜单数据
            insertSystemMenu(systemMenu);

            // 2. 处理轮播图关联
            if (carouselIds != null && carouselIds.length > 0) {
                associateCarousels(systemMenu.getMenuId(), carouselIds);
            }

            log.info("成功添加菜单，ID: {}", systemMenu.getMenuId());
            return systemMenu.getMenuId();
        } catch (Exception e) {
            log.error("添加菜单失败: 菜单信息={}, 轮播图IDs={}",
                    systemMenu, Arrays.toString(carouselIds), e);
            throw new BusinessException(ErrorCode.PARAMETER_ERROR.getCode(), ErrorCode.PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(SystemMenu systemMenu, Long[] carouselIds) {
        // 1. 参数校验
        if (systemMenu == null || systemMenu.getMenuId() == null) {
            throw new IllegalArgumentException("菜单信息或菜单ID不能为空");
        }

        // 2. 更新菜单基本信息
        systemMenu.setUpdateTime(new Date()); // 设置更新时间
        int affectedRows = systemMenuMapper.updateById(systemMenu);
        if (affectedRows <= 0) {
            throw new RuntimeException("菜单更新失败，可能菜单不存在");
        }

        // 3. 处理轮播图关联
        handleCarouselAssociation(systemMenu.getMenuId(), carouselIds);

        return affectedRows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long menuId) {
        // 1. 参数校验
        if (menuId == null || menuId <= 0) {
            throw new IllegalArgumentException("菜单ID不能为空且必须大于0");
        }

        // 2. 检查菜单是否存在
        SystemMenu existingMenu = systemMenuMapper.selectById(menuId);
        if (existingMenu == null) {
            throw new BusinessException(ErrorCode.MENU_NOT_FOUND.getCode(),ErrorCode.MENU_NOT_FOUND.getMessage());
        }

        // 3. 删除关联的轮播图关系（先删子表）
        deleteAssociatedCarousels(menuId);

        // 4. 删除菜单主表记录
        int affectedRows = systemMenuMapper.deleteById(menuId);
        if (affectedRows <= 0) {
            throw new RuntimeException("菜单删除失败");
        }

        log.info("成功删除菜单ID: {}", menuId);
        return affectedRows;
    }

    @Override
    public SystemMenu findByMenuId(Long menuId) {
        // 1. 参数校验
        if (menuId == null || menuId <= 0) {
            throw new IllegalArgumentException("菜单ID不能为空且必须大于0");
        }

        // 2. 查询菜单基本信息
        SystemMenu menu = systemMenuMapper.selectById(menuId);
        if (menu == null) {
            throw new BusinessException(ErrorCode.MENU_NOT_FOUND.getCode(), ErrorCode.MENU_NOT_FOUND.getMessage());
        }

        // 3. 加载关联的轮播图信息
        List<SystemCarousel> list = this.systemMenuMapper.findByMenuId(menuId);
        menu.setCarouselList(list);

        return menu;
    }

    /**
     * 参数校验
     */
    private void validateInput(SystemMenu systemMenu, Long[] carouselIds) {
        if (systemMenu == null) {
            throw new IllegalArgumentException("菜单信息不能为空");
        }

        if (carouselIds != null) {
            // 检查轮播图ID是否有效
            Set<Long> idSet = new HashSet<>();
            for (Long carouselId : carouselIds) {
                if (carouselId == null || carouselId <= 0) {
                    throw new IllegalArgumentException("轮播图ID无效");
                }
                if (!idSet.add(carouselId)) {
                    throw new IllegalArgumentException("存在重复的轮播图ID: " + carouselId);
                }
            }
        }
    }

    /**
     * 插入菜单数据
     */
    private void insertSystemMenu(SystemMenu systemMenu) {
        int affectedRows = systemMenuMapper.insert(systemMenu);
        if (affectedRows <= 0) {
            throw new RuntimeException("菜单插入失败");
        }

        if (systemMenu.getMenuId() == null) {
            throw new RuntimeException("菜单ID生成失败");
        }
    }

    /**
     * 关联轮播图
     */
    private void associateCarousels(Long menuId, Long[] carouselIds) {
        // 分批处理，每批100条
        int batchSize = 100;
        int total = carouselIds.length;

        for (int i = 0; i < total; i += batchSize) {
            int end = Math.min(i + batchSize, total);
            Long[] batch = Arrays.copyOfRange(carouselIds, i, end);

            List<SystemMenuCarousel> menuCarouselList = Arrays.stream(batch)
                    .map(carouselId -> {
                        SystemMenuCarousel menuCarousel = new SystemMenuCarousel();
                        menuCarousel.setMenuId(menuId);
                        menuCarousel.setCarouselId(carouselId);
                        return menuCarousel;
                    })
                    .collect(Collectors.toList());

            systemMenuCarouselMapper.insertBatch(menuCarouselList);
            log.debug("已关联轮播图批次: {}-{}, 菜单ID: {}", i, end, menuId);
        }
    }
    /**
     * 查询条件封装类
     */
    @RequiredArgsConstructor
    private static class QueryCondition<T> {
        private final T value;
        private final Consumer<T> action;

        void applyIfPresent() {
            if (value instanceof String str && StringUtils.isNotBlank(str)) {
                action.accept(value);
            } else if (value != null) {
                action.accept(value);
            }
        }
    }

    /**
     * 排序条件记录类
     */
    private record SortCondition(String field, String order) {}
    /**
     * 处理排序逻辑
     */
    private void handleSorting(SystemMenu systemMenu, LambdaQueryWrapper<SystemMenu> wrapper) {
        Optional.ofNullable(systemMenu.getSortField())
                .filter(StringUtils::isNotBlank)
                .flatMap(field -> Optional.ofNullable(systemMenu.getSortOrder())
                        .filter(StringUtils::isNotBlank)
                        .map(order -> new SortCondition(field, order)))
                .ifPresentOrElse(
                        condition -> {
                            Function<SystemMenu, ?> sortFunction = LambdaUtils.columnNameToFunction(condition.field());
                            if (sortFunction != null) {
                                boolean isAsc = "asc".equalsIgnoreCase(condition.order());
                                wrapper.orderBy(true, isAsc, (SFunction<SystemMenu, ?>) sortFunction);
                            }
                        },
                        () -> wrapper.orderByDesc(SystemMenu::getCreateTime) // 默认排序
                );
    }

    /**
     * 处理轮播图关联逻辑
     */
    private void handleCarouselAssociation(Long menuId, Long[] carouselIds) {
        // 3.1 先删除原有关联
        LambdaQueryWrapper<SystemMenuCarousel> deleteWrapper = new LambdaQueryWrapper<>();
        deleteWrapper.eq(SystemMenuCarousel::getMenuId, menuId);
        systemMenuCarouselMapper.delete(deleteWrapper);

        // 3.2 如果有新的轮播图ID，建立新关联
        if (carouselIds != null && carouselIds.length > 0) {
            List<SystemMenuCarousel> menuCarouselList = Arrays.stream(carouselIds)
                    .filter(Objects::nonNull)
                    .filter(id -> id > 0) // 过滤掉无效ID
                    .distinct() // 去重
                    .map(carouselId -> {
                        SystemMenuCarousel menuCarousel = new SystemMenuCarousel();
                        menuCarousel.setMenuId(menuId);
                        menuCarousel.setCarouselId(carouselId);
                        return menuCarousel;
                    })
                    .collect(Collectors.toList());

            if (!menuCarouselList.isEmpty()) {
                systemMenuCarouselMapper.insertBatch(menuCarouselList);
            }
        }
    }
    /**
     * 删除关联的轮播图关系
     */
    private void deleteAssociatedCarousels(Long menuId) {
        LambdaQueryWrapper<SystemMenuCarousel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemMenuCarousel::getMenuId, menuId);

        int deletedCount = systemMenuCarouselMapper.delete(wrapper);
        log.debug("删除菜单ID: {} 的 {} 条轮播图关联记录", menuId, deletedCount);
    }
}
