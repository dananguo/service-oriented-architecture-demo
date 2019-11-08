package com.soa.servicecomsumer.controller;

import com.soa.servicecomsumer.pojo.addParams;
import com.soa.servicecomsumer.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @Autowired
    HelloRemote remote;
    @GetMapping("/add")
    public String getResult(@RequestParam int left,@RequestParam  int right){
        return Integer.toString(remote.add(new addParams(left,right)));
    }
}
