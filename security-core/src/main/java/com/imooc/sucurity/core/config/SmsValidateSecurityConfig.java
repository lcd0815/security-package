package com.imooc.sucurity.core.config;

import com.imooc.sucurity.core.authentication.mobile.SmsCodeAuthenticationFilter;
import com.imooc.sucurity.core.authentication.mobile.SmsCodeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Description 在spring-security里面的过滤器链中加一个自己的Filter,要配置成功 失败后置处理,provider manager userDetailService
 **/
@Component
public class SmsValidateSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private UserDetailsService mobileUserDetailsService;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(mobileUserDetailsService);

        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
