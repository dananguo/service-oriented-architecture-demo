package com.soa.userservice.remote;

import com.soa.userservice.pojo.PersonInfo;
import com.soa.userservice.pojo.Stand_Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

@Primary
@FeignClient(name= "person-service",fallback = PersonRemoteHystrix.class)
public interface PersonRemote {
    //新建个人信息

    @PostMapping("/v1/Person")
    public Stand_Result NewPerson(@RequestBody PersonInfo personInfo);

    //查询个人信息

    @GetMapping("/v1/Person/{id}")
    public PersonInfo QueryPerson(@PathVariable("id") String id);

    //修改个人信息

    @PutMapping("/v1/Person")
    public Stand_Result Update(@RequestBody PersonInfo personInfo);

    //删除个人信息

    @DeleteMapping("/v1/Person/{id}")
    public Stand_Result Delete(@PathVariable("id") String id);
}
