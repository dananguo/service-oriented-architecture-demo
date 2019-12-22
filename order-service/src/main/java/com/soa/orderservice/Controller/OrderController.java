package com.soa.orderservice.Controller;

import com.rabbitmq.client.Channel;
import com.soa.orderservice.pojo.PayPrama;
import com.soa.orderservice.pojo.PurchaseParam;

import com.soa.orderservice.pojo.form;

import com.soa.orderservice.service.OrderService;
import com.spring4all.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@EnableSwagger2Doc
@RestController
@Slf4j
public class OrderController implements RabbitTemplate.ReturnCallback,RabbitTemplate.ConfirmCallback{

    @Autowired
    OrderService orderService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/v1/order/{id}")
    public List<form> FingOrderByID(@PathVariable("id") String id)
    {
        return orderService.findByCustomerId(id);
    }

    @RabbitListener(queues = "NewOrder")
    public void NewOrder(@Payload PurchaseParam purchaseParam, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deleverTag) throws IOException
    {
        try{
            form neworder=new form();
            neworder.setOrderId(purchaseParam.Order_id);
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
            channel.basicReject(deleverTag,true);
            //如果要回滚就在这提交增加库存任务。
        }

    }

    @RabbitListener(queues = "DeleteOrder")
    public void DeleteOrder(@Payload PurchaseParam purchaseParam, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deleverTag) throws IOException
    {
        try {
            orderService.DeleteOrder(purchaseParam.Order_id);
            channel.basicAck(deleverTag,false);
        }catch (Exception e)
        {
            log.info("删除订单时出错，重试");
            channel.basicReject(deleverTag,true);
        }
    }

    @RabbitListener(queues = "UpdateOrder")
    public void UpdateOrder(@Payload PayPrama payPrama, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deleverTag) throws IOException
    {

            if(payPrama.totalPrice==0)
            {
                try {
                    form order= orderService.findByOrderId(payPrama.orderId);
                    if(order.isState())//已经支付过了
                    {
                        channel.basicReject(deleverTag,false);
                        return;
                    }
                    payPrama.totalPrice=order.getSinglePrice()*order.getBookNum();//计算总价并填入
                    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                    rabbitTemplate.setMandatory(true);
                    rabbitTemplate.setConfirmCallback(this);
                    rabbitTemplate.setReturnCallback(this);
                    CorrelationData correlationData1=new CorrelationData(UUID.randomUUID().toString());
                    rabbitTemplate.convertAndSend("Money","UpdateMoney",payPrama,correlationData1);
                    channel.basicAck(deleverTag,false);
                }catch (Exception e)
                {
                    e.printStackTrace();
                    log.info("消息消费失败，不重试");
                    channel.basicReject(deleverTag,false);
                }

            }
            else
            {
                try {
                    form order=orderService.findByOrderId(payPrama.orderId);
                    order.setState(true);
                    orderService.save(order);
                    channel.basicAck(deleverTag,false);
                }catch (Exception e)
                {
                    e.printStackTrace();
                    log.info("消息消费失败，重试");
                    channel.basicReject(deleverTag,true);
                }

            }




    }


    @GetMapping("/v1/order/one/{orderid}")
    public form GetOrder(@PathVariable("orderid") String id)
    {

        form t=orderService.findByOrderId(id);

        return t;

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }
}
