package com.soa.authservice.remote;

import com.soa.authservice.pojo.AccountInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Primary
@FeignClient(name = "account-service", fallback = AccountRemoteHystrix.class)
public interface AccountRemote {
    //查询账户
    @PostMapping("/v1/Account/{id}")
    public AccountInfo QueryAccount(@PathVariable("id") String id);
}
