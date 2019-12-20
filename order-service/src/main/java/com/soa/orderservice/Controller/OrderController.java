package com.soa.orderservice.Controller;


import com.rabbitmq.client.Channel;
import com.soa.orderservice.pojo.PurchaseParam;
import com.soa.orderservice.pojo.order;
import com.soa.orderservice.service.OrderService;
import com.spring4all.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@EnableSwagger2Doc
@RestController
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/v1/order/{id}")
    public List<order> FindOrderByID(@PathVariable("id") String id)
    {
        return orderService.FindByCustomerId(id);
    }

    @RabbitListener(queues = "NewOrder")
    public void NewOrder(@Payload PurchaseParam purchaseParam, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deleverTag) throws IOException
    {
        try{
            order neworder=new order();
            neworder.setBookId(purchaseParam.Book_id);
            neworder.setBookNum(purchaseParam.Num);
            neworder.setCustomerId(purchaseParam.User_id);
            neworder.setSinglePrice(purchaseParam.price);
            neworder.setState(false);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            neworder.setTime(new Timestamp(System.currentTimeMillis()));
            orderService.save(neworder);
            //确认消费成功
            channel.basicAck(deleverTag,false);
        }catch (Exception e)
        {
            log.info("创建订单时失败，重试");
            channel.basicReject(deleverTag,false);
            //如果要回滚就在这提交增加库存任务。
        }

    }

}
