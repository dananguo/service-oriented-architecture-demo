package com.soa.orderservice.repository;

import com.soa.orderservice.pojo.order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<order,String > {
}
