package com.soa.purchaseservice.Remote;

import com.rabbitmq.client.Channel;
import com.soa.purchaseservice.param.PurchaseParam;
import com.soa.purchaseservice.pojo.form;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

@Primary
@FeignClient(name= "order-service")
public interface OrderRemote {

    @GetMapping("/v1/order/{id}")
    public List<form> FindOrderByID(@PathVariable("id") String id);

    @GetMapping("/v1/order/one/{orderid}")
    public form GetOrder(@PathVariable("orderid") String id);

//    @RabbitListener(queues = "NewOrder")
//    public void NewOrder(@Payload PurchaseParam purchaseParam, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deleverTag)throws IOException;
//
//    @RabbitListener(queues = "DeleteOrder")
//    public void DeleteOrder(@Payload PurchaseParam purchaseParam, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deleverTag)throws IOException;
}
