package com.soa.logisticsservice.repository;

import com.soa.logisticsservice.pojo.logistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsRepository extends JpaRepository<logistics,String > {
}
