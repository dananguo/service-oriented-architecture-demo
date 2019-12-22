package com.soa.userservice.controller;

import com.soa.userservice.pojo.*;
import com.soa.userservice.remote.AccountRemote;
import com.soa.userservice.remote.PersonRemote;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
@EnableSwagger2Doc
@RestController
@CrossOrigin(maxAge = 3600,origins = "*")
public class UserController {

    @Autowired
    AccountRemote accountRemote;
    @Autowired
    PersonRemote personRemote;

    @PostMapping("/v1/User/login")
    @ResponseBody
    public LoginResult login(@RequestBody LoginParams params){
        //调基础服务拿到结果

        LoginResult result=new LoginResult();
//       AccountInfo accountInfo=accountRemote.QueryAccount(params.getId());
        AccountInfo accountInfo=accountRemote.QueryAccount(params.getAccount());
       if(params.getPwd().equals(accountInfo.getPwd()))
       {

           result.setSucceed(true);
           result.setRole(accountInfo.getId());
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

    @PutMapping("/v1/User/Account")
    @ResponseBody
    public Stand_Result UpdateAccount(@RequestBody AccountInfo accountInfo)
    {

        Stand_Result result=accountRemote.Update(accountInfo);
        return result;
    }
    @PostMapping("/v1/User/Account")
    @ResponseBody
    public Sign_up_Result CreateAccount(@RequestBody Sign_up_params sign_up)
    {
        //调用基础服务得到结果,然后写聚合结果。
        Sign_up_Result result=accountRemote.CreateNew(sign_up);
        return result;
    }

    @PostMapping("/v1/User/PersonInfo")
    @ResponseBody
    public Stand_Result NewPerson(@RequestBody PersonInfo personInfo)
    {

        Stand_Result result=personRemote.NewPerson(personInfo);


        return result;
    }


    @PutMapping("/v1/User/PersonInfo")
    @ResponseBody
    public Stand_Result UpdatePerson(@RequestBody PersonInfo personInfo)
    {
        Stand_Result result= personRemote.Update(personInfo);
        return result;
    }

    @GetMapping("/v1/User/PersonInfo/{id}")
    @ResponseBody
    public PersonInfo GetPerson(@PathVariable("id") String id)
    {
        PersonInfo personInfo=personRemote.QueryPerson(id);
        return personInfo;
    }

    @DeleteMapping("/v1/User/PersonInfo/{id}")
    @ResponseBody
    public Stand_Result DeletePerson(@PathVariable("id") String id)
    {
        Stand_Result result=personRemote.Delete(id);
        return result;
    }
}
