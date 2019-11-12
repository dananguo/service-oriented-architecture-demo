package com.soa.userservice.remote;

import com.soa.userservice.pojo.PersonInfo;
import com.soa.userservice.pojo.Stand_Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

@Primary
@FeignClient(name= "spring-cloud-Person",fallback = PersonRemoteHystrix.class)
public interface PersonRemote {
    //新建个人信息

    @PostMapping("/Person")
    public Stand_Result NewPerson(@RequestBody PersonInfo personInfo);

    //查询个人信息

    @GetMapping("/Person")
    public PersonInfo QueryPerson(@RequestParam(value = "id") String id);

    //修改个人信息

    @PutMapping("/Person")
    public Stand_Result Update(@RequestBody PersonInfo personInfo);

    //删除个人信息

    @DeleteMapping("/Person")
    public Stand_Result Delete(@RequestParam(value = "id") String id);
}
