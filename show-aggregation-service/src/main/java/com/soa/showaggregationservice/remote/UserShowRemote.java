package com.soa.showaggregationservice.remote;

import com.soa.showaggregationservice.pojo.PersonInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Primary
@FeignClient(name= "user-show-service",fallback = UserShowRemoteHystrix.class)
public interface UserShowRemote {
    @GetMapping("/v1/user-show-by-id/{id}")
    public PersonInfo showUser(@PathVariable("id") String id);
    @GetMapping("/v1/user-show-by-name/{name}")
    public List showUserByName(@PathVariable("name") String name);


}
