package com.soa.purchaseservice.Remote;

import com.soa.purchaseservice.pojo.AccountInfo;
import com.soa.purchaseservice.pojo.Sign_up_Result;
import com.soa.purchaseservice.pojo.Sign_up_params;
import com.soa.purchaseservice.pojo.Stand_Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

@Primary
@FeignClient(name= "account-service",fallback = AccountRemoteHystrix.class)
public interface AccountRemote {
    //新建账户
    @PostMapping("/v1/Account")
    public Sign_up_Result CreateNew(@RequestBody Sign_up_params sign_up_params);

    //查询账户
    @GetMapping("/v1/Account/{id}")
    public AccountInfo QueryAccount(@PathVariable("id") String id);
    //修改账户
    @PutMapping("/v1/Account")
    public Stand_Result Update(@RequestBody AccountInfo accountInfo);
    //删除账户
    @DeleteMapping("/v1/Account/{id}")
    public Stand_Result Delete(@PathVariable("id") String id);

}
