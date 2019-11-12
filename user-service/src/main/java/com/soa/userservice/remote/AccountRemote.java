package com.soa.userservice.remote;

import com.soa.userservice.pojo.*;
import com.soa.userservice.pojo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

@Primary
@FeignClient(name= "spring-cloud-Account",fallback = AccountRemoteHystrix.class)
public interface AccountRemote {
    //新建账户
    @PostMapping("/Account")
    public Sign_up_Result CreateNew(@RequestBody Sign_up_params sign_up_params);

    //查询账户
    @GetMapping("/Account")
    public AccountInfo QueryAccount(@RequestParam(value = "id") String id);
    //修改账户
    @PutMapping("/Account")
    public Stand_Result Update(@RequestBody LoginParams loginParams);
    //删除账户
    @DeleteMapping("/Account")
    public Stand_Result Delete(@RequestParam(value = "id") String id);

}
