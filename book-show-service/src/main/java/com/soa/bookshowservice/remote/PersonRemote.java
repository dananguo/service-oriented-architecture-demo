package com.soa.bookshowservice.remote;

import com.soa.bookshowservice.pojo.PersonInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Primary
@FeignClient(name= "person-service",fallback = PersonRemoteHystrix.class)
public interface PersonRemote {
    @GetMapping("/v1/Person/{id}")
    public PersonInfo QueryPerson(@PathVariable("id") String id);
}
