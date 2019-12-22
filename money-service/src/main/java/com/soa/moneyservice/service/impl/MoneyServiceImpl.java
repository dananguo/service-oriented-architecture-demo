package com.soa.moneyservice.service.impl;

import com.soa.moneyservice.pojo.Money;
import com.soa.moneyservice.repository.MoneyRepository;
import com.soa.moneyservice.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyServiceImpl implements MoneyService {
    @Autowired
    MoneyRepository moneyRepository;

    @Override
    public Money FindMoneyById(String id) {
        try {
            return moneyRepository.findById(id).get();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
return new Money();
    }

    @Override
    public void UpdateMoney(Money money) {
        System.out.println(money.getId());
        moneyRepository.save(money);
    }
}
