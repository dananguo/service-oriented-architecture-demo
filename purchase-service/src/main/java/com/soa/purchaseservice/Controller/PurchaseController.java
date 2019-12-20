package com.soa.purchaseservice.Controller;


import com.netflix.discovery.converters.Auto;
import com.soa.purchaseservice.Remote.*;
import com.soa.purchaseservice.param.PurchaseParam;
import com.soa.purchaseservice.pojo.Book;
import com.soa.purchaseservice.pojo.Stand_Result;
import com.soa.purchaseservice.pojo.logistics;
import com.soa.purchaseservice.pojo.order;
import com.spring4all.swagger.EnableSwagger2Doc;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@EnableSwagger2Doc
@RestController
@Slf4j
public class PurchaseController implements RabbitTemplate.ReturnCallback,RabbitTemplate.ConfirmCallback{

    @Autowired
    AccountRemote accountRemote;
    @Autowired
    InventoryRemote inventoryRemote;
//    @Autowired
//    LogisticsRemote logisticsRemote;
//    @Autowired
//    MoneyRemote moneyRemote;
    @Autowired
    OrderRemote orderRemote;

    @Autowired
    RabbitTemplate rabbitTemplate;

    //购买商品
    @PostMapping("/v1/Purchase")
    public Stand_Result Purchase(@RequestBody PurchaseParam purchaseParam)
    {
        //交给库存服务
        try {
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setMandatory(true);
            rabbitTemplate.setConfirmCallback(this);
            rabbitTemplate.setReturnCallback(this);
            CorrelationData correlationData1=new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend("Inventory","UpdateInventory",purchaseParam,correlationData1);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        Stand_Result result= new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        result.setTime(null);

        return result;
    }

    //取消订单
    @DeleteMapping("/v1/Purchase")
    public Stand_Result CanclePurchase()
    {
        return new Stand_Result();
    }

    //支付订单
    @PostMapping("/v1/Pay")
    public Stand_Result Pay()
    {

        return new Stand_Result();
    }

    //查看订单
    @GetMapping("/v1/Purchase/{id}")
    public List<order> CheckPurchase(@PathVariable("id") String user_id)
    {
        List<order> list= orderRemote.FindOrderByID(user_id);
        return list;
    }
    //查看物流
    @GetMapping("/v1/Logistics/{id}")
    public Stand_Result CheckLogistics()
    {
        List<logistics> logistics=LogisticsRemote.FindLogById()



        Stand_Result result= new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        result.setTime(null);
        return result;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
            if(b)
            {
                log.info("消息执行成功");
            }
            else {
                log.info("消息执行失败，请重试");
            }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }
}
