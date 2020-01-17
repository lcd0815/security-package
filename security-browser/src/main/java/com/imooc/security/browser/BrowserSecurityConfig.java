package com.imooc.security.browser;

import com.imooc.sucurity.core.config.AbstractChannelSecurityConfig;
import com.imooc.sucurity.core.config.SmsValidateSecurityConfig;
import com.imooc.sucurity.core.config.ValidateCodeSecurityConfig;
import com.imooc.sucurity.core.constant.SecurityConstant;
import com.imooc.sucurity.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * lcd  2020/1/5
 * Description:
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private DataSource dataSource;
    @Qualifier("myUserDetailService")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SmsValidateSecurityConfig smsValidateSecurityConfig;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    //    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);
        http.apply(validateCodeSecurityConfig)
                .and()
            .apply(smsValidateSecurityConfig)
                .and().
            rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeTime())
                .userDetailsService(userDetailsService)
                .and()
            .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                .antMatchers("/account").permitAll()
                .antMatchers(SecurityConstant.LOGIN_ENTRANCE, securityProperties.getBrowser().getLoginPage()
                        , "/code/*"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
            .csrf().disable()
        ;
    }
}
