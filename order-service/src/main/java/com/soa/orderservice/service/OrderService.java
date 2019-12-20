package com.soa.orderservice.service;


import com.soa.orderservice.pojo.order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    public List<order> FindByCustomerId(String id);
    public List<order> FindAll();
    public void save(order neworder);
}
