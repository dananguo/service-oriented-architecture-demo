package com.soa.logisticsservice.Controller;

import com.rabbitmq.client.Channel;
import com.soa.logisticsservice.pojo.PayPrama;
import com.soa.logisticsservice.pojo.logistics;
import com.soa.logisticsservice.service.LogisticsService;
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

@EnableSwagger2Doc
@RestController
@Slf4j
public class LogisticsController {

    @Autowired
    LogisticsService logisticsService;

    @GetMapping("/v1/Logistics/{orderid}")
    public logistics getLogistics(@PathVariable("orderid") String id)
    {
        return logisticsService.findLogByOrderId(id);
    }

    @RabbitListener(queues = "NewLogistics")
    public void newLogistics(@Payload PayPrama payPrama, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deleverTag) throws IOException
    {
        try {
            logistics newlogistics=new logistics();
            newlogistics.setOrderid(payPrama.orderId);
            newlogistics.setState(true);
            newlogistics.setUserid(payPrama.userId);
            logisticsService.NewLogistics(newlogistics);
            channel.basicAck(deleverTag,true);
        }catch (Exception e)
        {
            //创建物流失败，重试
            e.printStackTrace();
            channel.basicReject(deleverTag,false);
        }

    }
}
