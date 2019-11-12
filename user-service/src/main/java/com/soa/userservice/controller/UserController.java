package com.soa.userservice.controller;

import com.soa.userservice.pojo.LoginParams;
import com.soa.userservice.pojo.LoginResult;
import com.soa.userservice.pojo.Sign_up_Result;
import com.soa.userservice.pojo.Sign_up_params;
import com.soa.userservice.remote.AccountRemote;
import com.soa.userservice.remote.PersonRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    AccountRemote accountRemote;
    @Autowired
    PersonRemote personRemote;

    @PostMapping("/login")
    @ResponseBody
    public LoginResult login(@RequestBody LoginParams params){
        //调基础服务拿到结果
        //boolean result = loginService.authenticate(params);
        LoginResult result=new LoginResult();
        //根据基础服务返回值，写返回给前端的结果,还没写

        return result;
    }

    @PostMapping("/CreateAccount")
    @ResponseBody
    public Sign_up_Result CreateAccount(@RequestBody Sign_up_params sign_up)
    {
        Sign_up_Result result=new Sign_up_Result();
        //调用基础服务得到结果,然后写聚合结果。
        result=accountRemote.CreateNew(sign_up);
        return result;
    }
}
