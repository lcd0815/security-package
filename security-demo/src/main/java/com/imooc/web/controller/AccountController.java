package com.imooc.web.controller;

import com.imooc.entity.Account;
import com.imooc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * lcd  2020/1/5
 * Description:
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PostMapping
    public Account add(@RequestBody Account account){
        return accountService.add(account);
    }
}
