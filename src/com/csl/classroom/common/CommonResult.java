package com.csl.classroom.common;

import cn.hutool.http.HttpStatus;
import lombok.Data;

/**
 * @author MaoLongLong
 * @date 2020-12-26 16:11
 */
@Data
public class CommonResult {

    private Integer code;
    private String message;
    private Object data;

    private CommonResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CommonResult ok(String message, Object data) {
        return new CommonResult(HttpStatus.HTTP_OK, message, data);
    }

    public static CommonResult ok(String message) {
        return new CommonResult(HttpStatus.HTTP_OK, message, null);
    }

    public static CommonResult error(String message, Object data) {
        return new CommonResult(HttpStatus.HTTP_INTERNAL_ERROR, message, data);
    }

    public static CommonResult error(String message) {
        return new CommonResult(HttpStatus.HTTP_INTERNAL_ERROR, message, null);
    }

    public static CommonResult unauthorized(String message) {
        return new CommonResult(HttpStatus.HTTP_UNAUTHORIZED, message, null);
    }
}
