package com.soa.moneyservice.repository;

import com.soa.moneyservice.pojo.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyRepository extends JpaRepository<Money,String > {
}
