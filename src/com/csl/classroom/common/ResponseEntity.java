package com.csl.classroom.common;

import cn.hutool.http.HttpStatus;
import lombok.Data;

/**
 * @author MaoLongLong
 * @date 2020-12-26 16:11
 */
@Data
public class ResponseEntity {

    private Integer code;
    private String message;
    private Object data;

    private ResponseEntity(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseEntity ok(String message, Object data) {
        return new ResponseEntity(HttpStatus.HTTP_OK, message, data);
    }

    public static ResponseEntity ok(String message) {
        return new ResponseEntity(HttpStatus.HTTP_OK, message, null);
    }

    public static ResponseEntity error(String message, Object data) {
        return new ResponseEntity(HttpStatus.HTTP_INTERNAL_ERROR, message, data);
    }

    public static ResponseEntity error(String message) {
        return new ResponseEntity(HttpStatus.HTTP_INTERNAL_ERROR, message, null);
    }

    public static ResponseEntity unauthorized(String message) {
        return new ResponseEntity(HttpStatus.HTTP_UNAUTHORIZED, message, null);
    }
}
