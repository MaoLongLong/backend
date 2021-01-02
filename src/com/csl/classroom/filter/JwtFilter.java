package com.csl.classroom.filter;

import cn.hutool.core.util.StrUtil;
import com.csl.classroom.common.ResponseEntity;
import com.csl.classroom.util.JsonUtil;
import com.csl.classroom.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MaoLongLong
 * @date 2020-12-28 13:19
 */
@Slf4j
public class JwtFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (JwtUtil.skip(req.getRequestURI())) {
            filterChain.doFilter(req, resp);
            return;
        }
        String token = req.getHeader("Authorization");
        if (JwtUtil.checkToken(token)) {
            String username = JwtUtil.getUsernameFromToken(token);
            if (StrUtil.isNotBlank(username)) {
                req.setAttribute("username", username);
                log.info("用户 [{}] 验证成功", username);
                filterChain.doFilter(req, resp);
            } else {
                JsonUtil.write(resp, ResponseEntity.unauthorized("获取用户名失败"));
            }
        } else {
            JsonUtil.write(resp, ResponseEntity.unauthorized("token无效"));
        }
    }

    @Override
    public void destroy() {
    }
}
