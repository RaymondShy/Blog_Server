package com.raymond.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    PARAM_ERROR(400, "参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_ERROR(500, "系统内部错误"),
    PARAMETER_ERROR(601,"参数异常"),
    MENU_NOT_FOUND(602,"菜单未找到");

    private final int code;
    private final String message;

    // 构造方法、getter等
}
