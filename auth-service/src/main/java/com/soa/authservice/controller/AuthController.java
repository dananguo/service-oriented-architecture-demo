package com.soa.authservice.controller;

import com.soa.authservice.pojo.LoginParams;
import com.soa.authservice.pojo.LoginResult;
import com.soa.authservice.remote.AccountRemote;
import com.soa.authservice.service.AuthService;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 19028
 * @date 2019/12/22 0:56
 */
@RestController
@CrossOrigin(maxAge = 3600,origins = "*")
@EnableSwagger2Doc
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
