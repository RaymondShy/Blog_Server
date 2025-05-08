package com.raymond.utils;

import com.raymond.domain.system.SystemMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LambdaUtils {

    // 用于缓存字段名和 Lambda 表达式的映射
    private static final Map<String, Function<SystemMenu, ?>> columnMap = new HashMap<>();

    static {
        // 初始化字段名到 Lambda 表达式的映射
        columnMap.put("menuName", SystemMenu::getMenuName);
        columnMap.put("menuUrl", SystemMenu::getMenuUrl);
        columnMap.put("createTime", SystemMenu::getCreateTime);
        // 添加更多字段的映射
    }

    // 根据字段名获取 Lambda 表达式
    public static <T> Function<T, ?> columnNameToFunction(String fieldName) {
        return (Function<T, ?>) columnMap.get(fieldName);
    }
}
