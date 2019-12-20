package com.soa.orderservice.service.impl;

import com.soa.orderservice.pojo.form;
import com.soa.orderservice.repository.OrderRepository;
import com.soa.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<form> FindByCustomerId(String id) {
       return orderRepository.findByCustomerId(id);
    }

    @Override
    public List<form> FindAll() {
        return orderRepository.findAll();
    }

    @Override
    public void save(order neworder) {
        orderRepository.save(neworder);
    }
}
