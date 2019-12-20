package com.soa.purchaseservice.Controller;


import com.netflix.discovery.converters.Auto;
import com.soa.purchaseservice.Remote.*;
import com.soa.purchaseservice.pojo.Stand_Result;
import com.spring4all.swagger.EnableSwagger2Doc;
import io.swagger.annotations.ApiOperation;
import org.junit.jupiter.api.Order;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableSwagger2Doc
@RestController
public class PurchaseController implements RabbitTemplate.ReturnCallback,RabbitTemplate.ConfirmCallback{

    @Autowired
    AccountRemote accountRemote;
    @Autowired
    InventoryRemote inventoryRemote;
//    @Autowired
//    LogisticsRemote logisticsRemote;
//    @Autowired
//    MoneyRemote moneyRemote;
//    @Autowired
//    OrderRemote orderRemote;

    @Autowired
    RabbitTemplate rabbitTemplate;

    //购买商品
    @PostMapping("/v1/Purchase")
    public Stand_Result Purchase()
    {

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
    @GetMapping("/v1/Purchase")
    public Stand_Result CheckPurchase()
    {
        return new Stand_Result();
    }
    //查看物流
    @GetMapping("/v1/Logistics")
    public Stand_Result CheckLogistics()
    {
        return new Stand_Result();
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }
}
