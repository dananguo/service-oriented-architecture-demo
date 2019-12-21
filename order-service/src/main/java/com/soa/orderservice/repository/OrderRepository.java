package com.soa.orderservice.repository;

import com.soa.orderservice.pojo.form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<form,String > {


    public List<form> findByCustomerId(String customerid);

}
