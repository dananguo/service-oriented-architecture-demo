package com.soa.orderservice.Controller;


import com.soa.orderservice.pojo.order;
import com.soa.orderservice.service.OrderService;
import com.spring4all.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableSwagger2Doc
@RestController
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/v1/order/{id}")
    public List<order> FingOrderByID(@PathVariable("id") String id)
    {
        return orderService.FindByCustomerId(id);
    }
}
