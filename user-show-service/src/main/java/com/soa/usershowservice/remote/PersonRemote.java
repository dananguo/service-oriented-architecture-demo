package com.soa.usershowservice.remote;

import com.soa.usershowservice.pojo.PersonInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Primary
@FeignClient(name= "spring-cloud-Book",fallback = PersonRemoteHystrix.class)
public interface PersonRemote {
    @GetMapping("/Person")
    public PersonInfo QueryPerson(@RequestParam(value = "id") String id);
}
