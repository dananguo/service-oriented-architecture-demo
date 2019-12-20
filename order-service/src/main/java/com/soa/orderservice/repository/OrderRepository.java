package com.soa.orderservice.repository;

import com.soa.orderservice.pojo.order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<order,String > {


    public List<order> findByCustomerId(String customerid);

}
