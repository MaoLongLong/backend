package com.csl.classroom.filter;

import cn.hutool.core.util.StrUtil;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author MaoLongLong
 * @date 2020-12-26 15:03
 */
public class CharacterEncodingFilter implements Filter {

    private static final String DEFAULT_ENCODING = "UTF-8";

    private String encoding = DEFAULT_ENCODING;

    @Override
    public void init(FilterConfig config) {
        String encoding = config.getInitParameter("encoding");
        if (StrUtil.isNotBlank(encoding)) {
            this.encoding = encoding;
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }

}
