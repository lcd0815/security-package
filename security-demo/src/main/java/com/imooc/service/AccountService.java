package com.imooc.service;

import com.imooc.dao.AccountDao;
import com.imooc.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * lcd  2020/1/5
 * Description:
 */
@Service
public class AccountService {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountDao accountDao;

    public Account add(Account account) {
//        account.setPassword(passwordEncoder.encode(account.getPassword()));
        Account save = accountDao.save(account);
        return save;
    }
}
