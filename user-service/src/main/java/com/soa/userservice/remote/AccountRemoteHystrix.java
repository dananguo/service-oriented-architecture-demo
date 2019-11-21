package com.soa.userservice.remote;

import com.soa.userservice.pojo.*;
import org.springframework.stereotype.Component;

@Component
public class AccountRemoteHystrix implements AccountRemote{

    @Override
    public Sign_up_Result CreateNew(Sign_up_params sign_up_params) {

        Sign_up_Result result=new Sign_up_Result();
        result.setId("WRONG");
        result.setSucceed(false);
        result.setWrongCode(1);
        return result;
    }

    @Override
    public AccountInfo QueryAccount(String id) {
      AccountInfo accountInfo=new AccountInfo();
      accountInfo.setAccount("WRONG");
      accountInfo.setId("调用基础服务失败");
      accountInfo.setPwd("WRONG");
      accountInfo.setRole("WRONG");
        return accountInfo;
    }

    @Override
    public Stand_Result Update(AccountInfo accountInfo) {
        Stand_Result result=new Stand_Result();
        result.setSucceed(false);
        result.setWrongCode("1");
        return result;
    }

    @Override
    public Stand_Result Delete(String id) {
        Stand_Result result=new Stand_Result();
        result.setSucceed(false);
        result.setWrongCode("1");
        return result;
    }
}
