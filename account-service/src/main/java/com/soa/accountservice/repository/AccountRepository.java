package com.soa.accountservice.repository;

import com.soa.accountservice.pojo.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhangyc
 * @date 2019/11/10 20:28
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    public Account findByAccount(String account);
}
