package com.soa.servicecomsumer.remote;

import com.soa.servicecomsumer.pojo.addParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

@Primary
@FeignClient(name= "spring-cloud-producer",fallback = HelloRemoteHystrix.class)
public interface HelloRemote {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(@RequestParam(value = "name") String name);
    @PostMapping("/add")
    public int add(@RequestBody addParams params);
}
