package com.imooc.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * lcd  2020/1/4
 * Description:
 */
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        long end = System.currentTimeMillis();
        System.out.println("filter 耗时"+(end-start));
    }

    @Override
    public void destroy() {

    }
}
