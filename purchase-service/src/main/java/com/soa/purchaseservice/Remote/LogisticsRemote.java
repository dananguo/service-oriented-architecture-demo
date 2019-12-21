package com.soa.purchaseservice.Remote;

import com.soa.purchaseservice.pojo.logistics;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Primary
@FeignClient(name= "logistics-service")
public interface LogisticsRemote {

    @GetMapping("/v1/Logistics/{orderid}")
    public logistics getLogistics(@PathVariable("orderid") String id);
}
