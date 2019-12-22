package com.soa.inventoryservice.controller;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.soa.inventoryservice.pojo.Book;
import com.soa.inventoryservice.pojo.PurchaseParam;
import com.soa.inventoryservice.pojo.Stand_Result;
import com.soa.inventoryservice.service.InventoryService;
import com.spring4all.swagger.EnableSwagger2Doc;
import feign.Body;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
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
import java.util.Map;
import java.util.UUID;

@EnableSwagger2Doc
@RestController
@Slf4j
public class InventoryController implements RabbitTemplate.ReturnCallback,RabbitTemplate.ConfirmCallback {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private RabbitTemplate rabbitTemplate;
//    //新建库存
//    @PostMapping("/v1/Inventory")
//    public String NewInventory(@RequestBody Book book) {
//        inventoryService.save(book);
//
//        return book.getBook_id();
//    }
@RabbitListener(queues = "NewInventory")
public void NewInventory(@Payload Book book, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long delivertTag) throws IOException {
    log.info("收到的对象："+book.getInventory_quantity());
    inventoryService.save(book);
    //手动签收
    channel.basicAck(delivertTag,false);
}

    //修改库存
    @RabbitListener(queues = "UpdateInventory")
    public Stand_Result Update(@Payload PurchaseParam purchaseParam, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long delivertTag) throws IOException {

    System.out.println("要改变的图书库存为"+purchaseParam.Num);

    try {
        Book book= inventoryService.findById(purchaseParam.Book_id);
        if(book.getInventory_quantity()-purchaseParam.Num>=0)//库存足够
        {
            //如果满足条件，进行修改
            book.setInventory_quantity(book.getInventory_quantity()-purchaseParam.Num);
            inventoryService.save(book);
            //此时，如果消息是为了购买，则提交创建订单任务
            if(purchaseParam.Num>0)
            {
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setMandatory(true);
                rabbitTemplate.setConfirmCallback(this);
                rabbitTemplate.setReturnCallback(this);
                CorrelationData correlationData1=new CorrelationData(UUID.randomUUID().toString());
                rabbitTemplate.convertAndSend("Order","NewOrder",purchaseParam,correlationData1);
                channel.basicAck(delivertTag,false);
            }
            else //此时，如果是为了取消订单，则创建删除订单任务。
            {
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setMandatory(true);
                rabbitTemplate.setConfirmCallback(this);
                rabbitTemplate.setReturnCallback(this);
                CorrelationData correlationData1=new CorrelationData(UUID.randomUUID().toString());
                rabbitTemplate.convertAndSend("Order","DeleteOrder",purchaseParam,correlationData1);
                channel.basicAck(delivertTag,false);
            }
        }
        else
        {
            //库存不足，拒绝
            channel.basicReject(delivertTag,false);
        }
    }catch (Exception e)
    {
        log.info("查找库存时失败");
        channel.basicReject(delivertTag,false);
    }


        Stand_Result result=new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        /*未加入操作时间*/
        return result;
    }

    //删除库存
    @DeleteMapping("/v1/Inventory/{id}")
    public Stand_Result DeleteInventory(@PathVariable("id") String id) {
        inventoryService.delete(id);
        Stand_Result result=new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        /*未加入操作时间*/
        return result;
    }
    //查询库存
    @GetMapping("/v1/Inventory/{id}")
    public Book QueryInventory(@PathVariable("id") String id){
        return inventoryService.findById(id);
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if(b)
        {
            log.info("消息执行成功");
        }
        else {
            log.info("消息执行失败，重试");

        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }
}
