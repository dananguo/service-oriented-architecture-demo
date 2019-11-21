package com.soa.usershowservice.remote;

import com.soa.usershowservice.pojo.PersonInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Primary
@FeignClient(name= "person-service",fallback = PersonRemoteHystrix.class)
public interface PersonRemote {
    @GetMapping("/v1/Person/{id}")
    public PersonInfo QueryPerson(@PathVariable("id") String id);
    @GetMapping("/v1/person-by-name/{name}")
    public List QueryPersonByName(@PathVariable("name") String name);
}
