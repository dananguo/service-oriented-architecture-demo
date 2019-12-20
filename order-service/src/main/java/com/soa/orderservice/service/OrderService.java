package com.soa.orderservice.service;


import com.soa.orderservice.pojo.form;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    public List<form> FindByCustomerId(String id);
    public List<form> FindAll();

}
