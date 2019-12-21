package com.soa.logisticsservice.service.impl;

import com.soa.logisticsservice.pojo.logistics;
import com.soa.logisticsservice.repository.LogisticsRepository;
import com.soa.logisticsservice.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticsServiceImpl implements LogisticsService {
    @Autowired
    LogisticsRepository logisticsRepository;

    @Override
    public void NewLogistics(logistics newlogistics) {
        logisticsRepository.save(newlogistics);
    }

    @Override
    public logistics findLogByOrderId(String orderid) {
        return logisticsRepository.findByOrderid(orderid);
    }
}
