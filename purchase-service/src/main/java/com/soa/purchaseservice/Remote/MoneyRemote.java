package com.soa.purchaseservice.Remote;

import com.soa.purchaseservice.pojo.Money;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Primary
@FeignClient(name= "money-service")
public interface MoneyRemote {

    @GetMapping("/v1/Money/{userid}")
    public Money GetMoney(@PathVariable("userid") String id);


}
