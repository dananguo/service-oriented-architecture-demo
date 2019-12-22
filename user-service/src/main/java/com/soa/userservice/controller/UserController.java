package com.soa.userservice.controller;

import com.soa.userservice.pojo.*;
import com.soa.userservice.remote.AccountRemote;
import com.soa.userservice.remote.PersonRemote;
import com.spring4all.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@EnableSwagger2Doc
@RestController
@CrossOrigin(maxAge = 3600,origins = "*")
@Slf4j
public class UserController implements RabbitTemplate.ReturnCallback,RabbitTemplate.ConfirmCallback{

    @Autowired
    AccountRemote accountRemote;
    @Autowired
    PersonRemote personRemote;

    @Autowired
    RabbitTemplate rabbitTemplate;

    /*@PostMapping("/v1/User/login")
    @ResponseBody
    public LoginResult login(@RequestBody LoginParams params){
        //调基础服务拿到结果

        LoginResult result=new LoginResult();
       AccountInfo accountInfo=accountRemote.QueryAccount(params.getAccount());
       if(params.getPwd().equals(accountInfo.getPwd()))
       {

           result.setSucceed(true);
           result.setRole(accountInfo.getId());
           SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
           result.setLogin_Time(df.format(new Date()));
           result.setUserId(accountInfo.id);
       }
       else
       {
           result.setLogin_Time(null);
           result.setRole(null);
           result.setSucceed(false);
           result.setWromgCode(1);
       }
        return result;
    }*/
    @PutMapping("/v1/User/Account")
    @ResponseBody
    public Stand_Result UpdateAccount(@RequestBody AccountInfo accountInfo)
    {

        Stand_Result result=accountRemote.Update(accountInfo);
        return result;
    }
    @PostMapping("/v1/User/Account")
    @ResponseBody
    public Stand_Result CreateAccount(@RequestBody Sign_up_params signUp)
    {
        //调用基础服务得到结果,然后写聚合结果。
        //先建账户，然后补单积分和个人信息
        //先查看数据库中有无重名账户，有则返回错误码
        try{
            AccountInfo accountInfo= accountRemote.QueryAccount(signUp.Account);
            if(accountInfo.getAccount().equals("无此账户"))
            {
                try {
                    //可以开始创建
                    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                    rabbitTemplate.setMandatory(true);
                    rabbitTemplate.setConfirmCallback(this);
                    rabbitTemplate.setReturnCallback(this);
                    CorrelationData correlationData1=new CorrelationData(UUID.randomUUID().toString());
                    rabbitTemplate.convertAndSend("Account","NewAccount",signUp,correlationData1);
                }catch (Exception e)
                {
                    e.printStackTrace();
                    Stand_Result result=new Stand_Result();
                    result.setErrorMessage("消息队列错误");
                    result.setSucceed(false);
                    result.setWrongCode("103");
                    Date date=new Date();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                    result.setTime(df.format(date));
                    return result;
                }

            }
            else{
                //系统中已经存在此账户
                Stand_Result result=new Stand_Result();
                result.setErrorMessage("账户重复");
                result.setSucceed(false);
                result.setWrongCode("101");
                Date date=new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                result.setTime(df.format(date));
                return result;
            }

//            Stand_Result result=new Stand_Result();
//            result.setTime("casdf");
//            return result;
        }catch (Exception e)
        {
            e.printStackTrace();
            //创建失败
            Stand_Result result=new Stand_Result();
            result.setErrorMessage("Account微服务异常");
            result.setSucceed(false);
            result.setWrongCode("102");
            Date date=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            result.setTime(df.format(date));
            return result;
        }
        Stand_Result result=new Stand_Result();
        result.setSucceed(true);
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        result.setTime(df.format(date));

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

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }
}
