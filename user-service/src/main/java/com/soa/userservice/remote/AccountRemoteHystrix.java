package com.soa.userservice.remote;

import com.soa.userservice.pojo.*;

public class AccountRemoteHystrix implements AccountRemote{

    @Override
    public Sign_up_Result CreateNew(Sign_up_params sign_up_params) {
        return null;
    }

    @Override
    public AccountInfo QueryAccount(String id) {
        return null;
    }

    @Override
    public Stand_Result Update(AccountInfo accountInfo) {
        return null;
    }

    @Override
    public Stand_Result Delete(String id) {
        return null;
    }
}
