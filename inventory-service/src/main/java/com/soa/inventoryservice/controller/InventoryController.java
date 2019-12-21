package com.soa.inventoryservice.controller;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.soa.inventoryservice.pojo.Book;
import com.soa.inventoryservice.pojo.Stand_Result;
import com.soa.inventoryservice.service.InventoryService;
import com.spring4all.swagger.EnableSwagger2Doc;
import feign.Body;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@EnableSwagger2Doc
@RestController
@Slf4j
public class InventoryController {

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
    @PutMapping("/v1/Inventory")
    public Stand_Result Update(@RequestBody Book book) {
        inventoryService.save(book);

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


}
