package com.imooc.sucurity.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * lcd  2020/1/16
 * Description:
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal);
        Object details = authentication.getDetails();
        SmsCodeAuthenticationToken smsCodeAuthenticationToken = new SmsCodeAuthenticationToken(principal, userDetails.getAuthorities());
        smsCodeAuthenticationToken.setDetails(details);
        return smsCodeAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
