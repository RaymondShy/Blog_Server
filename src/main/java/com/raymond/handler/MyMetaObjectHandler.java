package com.raymond.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 配置MyBatis-Plus的自动填充功能
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 注册时间自动填充
        this.strictInsertFill(metaObject, "register_time", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 最后登录时间自动填充
        this.strictUpdateFill(metaObject, "last_login_time", Date.class, new Date());
    }
}
