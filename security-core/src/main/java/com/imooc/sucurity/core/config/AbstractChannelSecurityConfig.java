package com.imooc.sucurity.core.config;

import com.imooc.sucurity.core.constant.SecurityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * lcd  2020/1/17
 * Description:表单登陆的
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler imoocSuccessAuthenticationHandler;
    @Autowired
    private AuthenticationFailureHandler imoocAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                //后端关口
                .loginPage(SecurityConstant.LOGIN_ENTRANCE)
                //要跟html页面的地址一致!!前端入口
                .loginProcessingUrl(SecurityConstant.PASSWORD_LOGIN_URL)
                .successHandler(imoocSuccessAuthenticationHandler)
                .failureHandler(imoocAuthenticationFailureHandler);
    }
}
