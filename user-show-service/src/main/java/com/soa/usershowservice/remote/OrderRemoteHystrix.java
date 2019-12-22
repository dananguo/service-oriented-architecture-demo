package com.soa.usershowservice.remote;

import com.soa.usershowservice.pojo.form;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: finalProject
 * @description:
 * @author: Yu Liu
 * @create: 2019/12/22
 **/
@Component
public class OrderRemoteHystrix implements OrderRemote{
    @GetMapping("/v1/order/{id}")
    public List<form> FingOrderByID(@PathVariable("id") String id){
        return new ArrayList<>();
    }
}