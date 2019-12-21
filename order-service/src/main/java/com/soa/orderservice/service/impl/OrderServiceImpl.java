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
    public List<form> findByCustomerId(String id) {
       return orderRepository.findByCustomerId(id);
    }

    @Override
    public List<form> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void save(form neworder) {
        orderRepository.save(neworder);
    }

    @Override
    public form findByOrderId(String orderid) {


        return orderRepository.findById(orderid).get();
    }
    @Override
    public void DeleteOrder(String id)
    {
        orderRepository.deleteById(id);
    }
}
