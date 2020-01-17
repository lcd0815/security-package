package com.imooc.config;

import com.imooc.filter.TimeFilter;
import com.imooc.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * lcd  2020/1/4
 * Description:
 */
//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
//    @Autowired
//    private TimeInterceptor timeInterceptor;

//    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new TimeFilter());
        filterRegistrationBean.addUrlPatterns("/user/*");
        filterRegistrationBean.setName("timeFilter");
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timeInterceptor);
//    }
}
