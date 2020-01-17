package com.imooc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * lcd  2020/1/4
 * Description:
 */
//@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("TimeInterceptor preHandle");
        httpServletRequest.setAttribute("time",System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("TimeInterceptor postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("TimeInterceptor afterCompletion");
        HandlerMethod handlerMethod = (HandlerMethod) o;
        System.out.println(handlerMethod.getBean().getClass().getName());
        System.out.println(handlerMethod.getMethod().getName());
        System.out.println("interceptor exception"+e);
        long time = (long) httpServletRequest.getAttribute("time");
        System.out.println("TimeInterceptor 耗时"+(System.currentTimeMillis()-time));
    }
}
