package com.soa.authservice.controller;

import com.soa.authservice.pojo.LoginParams;
import com.soa.authservice.pojo.LoginResult;
import com.soa.authservice.remote.AccountRemote;
import com.soa.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 19028
 * @date 2019/12/22 0:56
 */
@RestController
public class AuthController {

    @Autowired
    AccountRemote accountRemote;

    @Autowired
    AuthService authService;

    @PostMapping("/v1/User/login")
    @ResponseBody
    public LoginResult login(@RequestBody LoginParams params) {
        //登录校验并签发token

        String account = params.getAccount();
        String password = params.getPwd();
        LoginResult result = authService.authentication(account, password);
        return result;
    }

}
