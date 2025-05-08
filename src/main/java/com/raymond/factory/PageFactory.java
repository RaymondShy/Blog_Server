package com.raymond.factory;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.domain.BaseQuery;

/**
 * 分页构造器
 */
public class PageFactory {

    public static <T extends BaseQuery> Page<?> defaultPage(T query) {
        int pageNum = query.getPageNum() != null && query.getPageNum() > 0 ? query.getPageNum() : 1;
        int pageSize = query.getPageSize() != null && query.getPageSize() > 0 ? query.getPageSize() : 10;
        return new Page<>(pageNum, pageSize);
    }

}
