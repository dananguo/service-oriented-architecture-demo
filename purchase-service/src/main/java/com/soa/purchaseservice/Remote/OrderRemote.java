package com.soa.purchaseservice.Remote;

import com.soa.purchaseservice.pojo.order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Primary
@FeignClient(name= "order-service")
public interface OrderRemote {

    @GetMapping("/v1/order/{id}")
    public List<order> FingOrderByID(@PathVariable("id") String id);
}
