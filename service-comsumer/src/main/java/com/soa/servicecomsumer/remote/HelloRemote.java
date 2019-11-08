package com.soa.servicecomsumer.remote;

import com.soa.servicecomsumer.pojo.addParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name= "spring-cloud-producer")
public interface HelloRemote {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(@RequestParam(value = "name") String name);
    @PostMapping("/add")
    public int add(@RequestBody addParams params);
}
