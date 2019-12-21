package com.soa.accountservice.Service.impl;

import com.soa.accountservice.Service.AccountService;
import com.soa.accountservice.pojo.Account;
import com.soa.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceimpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findById(String id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public void delete(String id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void deleteAll() {
        accountRepository.deleteAll();
    }

    @Override
    public boolean checkAccount(String id) {
        if(accountRepository.findByAccount(id)!=null)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
}
