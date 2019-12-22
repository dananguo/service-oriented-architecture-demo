package com.soa.usershowservice.remote;

import com.soa.usershowservice.pojo.form;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Primary
@FeignClient(name= "order-service",fallback = OrderRemoteHystrix.class)
public interface OrderRemote {
    @GetMapping("/v1/order/{id}")
    public List<form> FingOrderByID(@PathVariable("id") String id);

}
