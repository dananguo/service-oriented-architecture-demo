package com.soa.usershowservice.remote;

import com.soa.usershowservice.pojo.Money;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Primary
@FeignClient(name= "money-service",fallback = MoneyRemoteHystrix.class)
public interface MoneyRemote {
    @GetMapping("/v1/Money/{userid}")
    public Money GetMoney(@PathVariable("userid") String id);
}
