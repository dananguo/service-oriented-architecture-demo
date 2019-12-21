package com.soa.accountservice.controller;

import com.rabbitmq.client.Channel;
import com.soa.accountservice.Service.AccountService;
import com.soa.accountservice.pojo.*;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.apache.ibatis.annotations.Delete;
import org.hibernate.cache.spi.AbstractCacheTransactionSynchronization;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@EnableSwagger2Doc
@RestController
public class AccountController implements RabbitTemplate.ReturnCallback,RabbitTemplate.ConfirmCallback{

    @Autowired
    private AccountService accountService;

    @Autowired
    RabbitTemplate rabbitTemplate;
    //新建账户
//    @PostMapping("/v1/Account")
//    public Sign_up_Result CreateNew(@RequestBody Sign_up_params sign_up_params) {
//        Account account=new Account();
//        account.setAccount(sign_up_params.getAccount());
//        account.setPwd(sign_up_params.getPwd());
//        account.setRole(sign_up_params.getRole());
//        accountService.save(account);
//        Sign_up_Result result=new Sign_up_Result();
//        result.setId(account.get_id());
//        result.setSucceed(true);
//        result.setWrongCode(0);
//        return result;
//    }

    @RabbitListener(queues = "NewAccount")
    public Stand_Result NewAccount(@Payload Sign_up_params sign_up_params, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long delivertTag) throws IOException
    {
        try {
            Account account=new Account();
            String id=UUID.randomUUID().toString();
            account.set_id(id);
        account.setAccount(sign_up_params.getAccount());
        account.setPwd(sign_up_params.getPwd());
        account.setRole(sign_up_params.getRole());
            accountService.save(account);

            //如果创建了账户，就建立个人信息及积分
            sign_up_params.id=id;
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setMandatory(true);
            rabbitTemplate.setConfirmCallback(this);
            rabbitTemplate.setReturnCallback(this);
            CorrelationData correlationData1=new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend("Person","NewPerson",sign_up_params,correlationData1);
            CorrelationData correlationData2=new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend("Money","NewMoney",sign_up_params,correlationData2);
            channel.basicAck(delivertTag,false);
        }catch (Exception e)
        {
            //创建失败，不重试
            e.printStackTrace();
            channel.basicReject(delivertTag,false);
        }

        return null;
    }
    //查询账户
    @GetMapping("/v1/Account/{id}")
    public AccountInfo QueryAccount(@PathVariable("id") String id) {
        Account account=new Account();
        account=accountService.findById(id);
        AccountInfo accountInfo=new AccountInfo();
        accountInfo.setId(account.get_id());
        accountInfo.setAccount(account.getAccount());
        accountInfo.setPwd(account.getPwd());
        accountInfo.setRole(account.getRole());
        return accountInfo;
    }

    //修改账户
    @PutMapping("/v1/Account")
    public Stand_Result Update(@RequestBody AccountInfo accountInfo) {
        System.out.println(accountInfo);
        Account account=new Account();
        account.set_id(accountInfo.getId());
        account.setAccount(accountInfo.getAccount());
        account.setPwd(accountInfo.getPwd());
        account.setRole(accountInfo.getRole());
        accountService.save(account);

        Stand_Result result=new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        return result;
    }

    //删除账户
    @DeleteMapping("/v1/Account/{id}")
    public Stand_Result Delete(@PathVariable("id") String id) {
        accountService.delete(id);
        Stand_Result result=new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        return result;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }
}
