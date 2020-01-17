package com.imooc.service;

import com.imooc.dao.AccountDao;
import com.imooc.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * lcd  2020/1/5
 * Description:
 */
@Component
public class MyUserDetailService implements UserDetailsService {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountDao accountDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (username.equalsIgnoreCase("13012345678")) {
//            return new User(username,"")
//        }
//        Account account = accountDao.findAccountByUsername(username);
//        User user = new User(account.getUsername(),account.getPassword()
//                ,account.getEnabled(),account.getExpired(),true,true
//                , AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//        return user;
        return new User(username, ("123"), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

    }
}
