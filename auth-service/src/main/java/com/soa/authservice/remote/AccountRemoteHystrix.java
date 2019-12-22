package com.soa.authservice.remote;

import com.soa.authservice.pojo.AccountInfo;
import org.springframework.stereotype.Component;

@Component
public class AccountRemoteHystrix implements AccountRemote {
    @Override
    public AccountInfo QueryAccount(String id) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccount("WRONG");
        accountInfo.setId("调用基础服务失败");
        accountInfo.setPwd("WRONG");
        accountInfo.setRole("WRONG");
        return accountInfo;
    }
}
