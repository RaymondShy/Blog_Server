package com.raymond.exception;

/**
 * 业务异常类
 * 用于封装业务逻辑中的异常情况
 */
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误信息
     */
    private final String message;

    /**
     * 构造方法
     * @param message 错误信息
     */
    public BusinessException(String message) {
        super(message);
        this.code = 500; // 默认错误码
        this.message = message;
    }

    /**
     * 构造方法
     * @param code 错误码
     * @param message 错误信息
     */
    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 构造方法
     * @param message 错误信息
     * @param cause 原始异常
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.code = 500; // 默认错误码
        this.message = message;
    }

    /**
     * 构造方法
     * @param code 错误码
     * @param message 错误信息
     * @param cause 原始异常
     */
    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    /**
     * 获取错误码
     * @return 错误码
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 获取错误信息
     * @return 错误信息
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 重写toString方法
     * @return 异常信息字符串
     */
    @Override
    public String toString() {
        return "BusinessException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
