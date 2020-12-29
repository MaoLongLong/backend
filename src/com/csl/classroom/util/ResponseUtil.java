package com.csl.classroom.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MaoLongLong
 * @date 2020-12-26 16:18
 */
public class ResponseUtil {

    private ResponseUtil() {
    }

    public static <T> void writeJson(HttpServletResponse response, T obj) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(obj));
    }

}
