package com.csl.classroom.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author MaoLongLong
 * @date 2020-12-26 16:18
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper OM;

    static {
        OM = new ObjectMapper();
        OM.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        OM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OM.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    }

    private JsonUtil() {
    }

    public static <T> void write(HttpServletResponse response, T obj) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(OM.writeValueAsString(obj));
        out.flush();
        out.close();
    }

    public static <T> T read(HttpServletRequest req, Class<T> type) throws IOException {
        return OM.readValue(req.getReader(), type);
    }

}
