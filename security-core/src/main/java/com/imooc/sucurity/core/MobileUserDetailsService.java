package com.imooc.sucurity.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * lcd  2020/1/16
 * Description:如果spring里面有2个UserDetailsService
 * 那么filter对应的provider中的service就不知道找谁了 就是空的
 */
//@Component("mobileUserDetailsService")
public class MobileUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        User user = new User(mobile,"123456"
                ,true,true,true,true
                , AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}
