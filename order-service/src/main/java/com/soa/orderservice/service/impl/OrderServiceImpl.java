package com.soa.orderservice.service.impl;

import com.soa.orderservice.pojo.order;
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
    public List<order> FindByCustomerId(String id) {
       return orderRepository.findByCustomerId(id);
    }

    @Override
    public List<order> FindAll() {
        return orderRepository.findAll();
    }
}
