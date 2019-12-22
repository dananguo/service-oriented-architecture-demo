package com.soa.logisticsservice.service;

import com.soa.logisticsservice.pojo.logistics;
import org.springframework.stereotype.Service;

@Service
public interface LogisticsService {
    public void NewLogistics(logistics newlogistics);
    public logistics findLogByOrderId(String orderid);
}
