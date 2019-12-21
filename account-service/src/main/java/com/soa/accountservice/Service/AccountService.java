package com.soa.accountservice.Service;

import com.soa.accountservice.pojo.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    public Account findById(String id);

    public List<Account> findAll();

    public void delete(String id);

    public void save(Account account);

    public void deleteAll();

    public boolean checkAccount(String id);
}
