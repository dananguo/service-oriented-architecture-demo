package com.soa.userservice.controller;

import com.soa.userservice.pojo.*;
import com.soa.userservice.remote.AccountRemote;
import com.soa.userservice.remote.PersonRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        //根据基础服务返回值，写返回给前端的结果,还没写
        LoginResult result=new LoginResult();
       AccountInfo accountInfo=accountRemote.QueryAccount(params.getId());
       if(params.getPwd().equals(accountInfo.getPwd()))
       {
           result.setWromgCode(0);
           result.setSucceed(true);
           result.setRole(accountInfo.getRole());
           SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
           result.setLogin_Time(df.format(new Date()));
       }
       else
       {
           result.setLogin_Time(null);
           result.setRole(null);
           result.setSucceed(false);
           result.setWromgCode(1);
       }
        return result;
    }

    @PostMapping("/CreateAccount")
    @ResponseBody
    public Sign_up_Result CreateAccount(@RequestBody Sign_up_params sign_up)
    {
        //调用基础服务得到结果,然后写聚合结果。
        Sign_up_Result result=accountRemote.CreateNew(sign_up);
        return result;
    }

    @PostMapping("/PersonInfo")
    @ResponseBody
    public Stand_Result NewPerson(@RequestBody PersonInfo personInfo)
    {

        Stand_Result result=personRemote.NewPerson(personInfo);


        return result;
    }

    @GetMapping("/PersonInfo")
    @ResponseBody
    public PersonInfo GetPerson(@RequestParam String id)
    {
        PersonInfo personInfo=personRemote.QueryPerson(id);
        return personInfo;
    }

    @DeleteMapping("/PersonInfo")
    @ResponseBody
    public Stand_Result DeletePerson(@RequestParam String id)
    {
        Stand_Result result=personRemote.Delete(id);
        return result;
    }
}
