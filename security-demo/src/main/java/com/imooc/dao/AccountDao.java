package com.imooc.dao;

import com.imooc.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * lcd  2020/1/5
 * Description:
 */
@Repository
public interface AccountDao extends JpaRepository<Account,Long> {
    public Account findAccountByUsername(String username);
}
