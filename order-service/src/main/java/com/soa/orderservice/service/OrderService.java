package com.soa.orderservice.service;


import com.soa.orderservice.pojo.form;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    public List<form> findByCustomerId(String id);
    public List<form> findAll();
    public void save(form neworder);
    public form findByOrderId(String id );
    public void DeleteOrder(String id);
}
