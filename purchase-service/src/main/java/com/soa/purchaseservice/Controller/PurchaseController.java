package com.soa.purchaseservice.Controller;


import com.netflix.discovery.converters.Auto;
import com.soa.purchaseservice.Remote.*;
import com.soa.purchaseservice.param.PayPrama;
import com.soa.purchaseservice.param.PurchaseParam;
import com.soa.purchaseservice.pojo.*;
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
@CrossOrigin(maxAge = 3600,origins = "*")
public class PurchaseController implements RabbitTemplate.ReturnCallback,RabbitTemplate.ConfirmCallback{

    @Autowired
    AccountRemote accountRemote;

    @Autowired
    InventoryRemote inventoryRemote;

    @Autowired
    LogisticsRemote logisticsRemote;
    @Autowired
    MoneyRemote moneyRemote;
    @Autowired
    OrderRemote orderRemote;

    @Autowired
    RabbitTemplate rabbitTemplate;

    //购买商品
    @PostMapping("/v1/Purchase")
    public Stand_Result Purchase(@RequestBody PurchaseParam purchaseParam)
    {
        String id=UUID.randomUUID().toString();//生成订单id
        purchaseParam.Order_id=id;
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
        result.setTime(id);

        return result;
    }

    //取消订单
    @DeleteMapping("/v1/Purchase/{id}")
    public Stand_Result CanclePurchase(@PathVariable("id") String id)
    {
        //交给库存服务
        try {
            PurchaseParam purchaseParam=new PurchaseParam();
            form order=orderRemote.GetOrder(id);
            log.info(id);
            log.info(order.getBookId());
            purchaseParam.Book_id=order.getBookId();
            purchaseParam.User_id=order.getCustomerId();
            purchaseParam.price=order.getSinglePrice();
            purchaseParam.Num=0-order.getBookNum();
            purchaseParam.Order_id=id;
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

    //支付订单
    @PostMapping("/v1/Pay/")
    public Stand_Result Pay(@RequestBody PayPrama payPrama)
    {
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setMandatory(true);
            rabbitTemplate.setConfirmCallback(this);
            rabbitTemplate.setReturnCallback(this);
            CorrelationData correlationData1=new CorrelationData(UUID.randomUUID().toString());
            payPrama.totalPrice=0;
            rabbitTemplate.convertAndSend("Order","UpdateOrder",payPrama,correlationData1);


        return new Stand_Result();
    }

    //查看订单
    @GetMapping("/v1/Purchase/{id}")
    public List<form> CheckPurchase(@PathVariable("id") String user_id)
    {
        List<form> list= orderRemote.FindOrderByID(user_id);
        return list;
    }
    //查看物流
    @GetMapping("/v1/Logistics/{id}")
    public logistics CheckLogistics(@PathVariable("id") String id)
    {
        logistics logistics= logisticsRemote.getLogistics(id);
        return logistics;
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
