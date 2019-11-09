package com.soa.userservice.controller;

import com.soa.userservice.pojo.LoginParams;
import com.soa.userservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private final LoginService loginService;

    public UserController(LoginService loginService) {
        this.loginService = loginService;
    }
    @PostMapping("/login")
    @ResponseBody
    public Map login(@RequestBody LoginParams params){
        boolean result = loginService.authenticate(params);
        Map<String, Object> resultMap = new HashMap<>();
        if(result){
            resultMap.put("result",true);
            resultMap.put("info","You have successfully login");
        }else {
            resultMap.put("result",false);
            resultMap.put("info","Please try another password");
        }
        return resultMap;
    }
}
