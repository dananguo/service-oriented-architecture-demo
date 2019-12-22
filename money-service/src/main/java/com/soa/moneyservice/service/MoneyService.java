package com.soa.moneyservice.service;


import com.soa.moneyservice.pojo.Money;
import org.springframework.stereotype.Service;

@Service
public interface MoneyService {
    public Money FindMoneyById(String id);
    public void UpdateMoney(Money money);
}
