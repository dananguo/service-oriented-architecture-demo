package com.soa.logisticsservice.Controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.soa.logisticsservice.pojo.PayPrama;
import com.soa.logisticsservice.pojo.logistics;
import com.soa.logisticsservice.service.LogisticsService;
import com.spring4all.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import java.io.IOException;
import java.util.UUID;

@EnableSwagger2Doc
@RestController
@Slf4j
public class LogisticsController implements RabbitTemplate.ReturnCallback,RabbitTemplate.ConfirmCallback{

    @Autowired
    LogisticsService logisticsService;

    @Autowired
    RabbitTemplate rabbitTemplate;

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
            newlogistics.setState(false);
            newlogistics.setUserid(payPrama.userId);
            logisticsService.NewLogistics(newlogistics);
            channel.basicAck(deleverTag,false);


            //创建延迟任务
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setMandatory(true);
            rabbitTemplate.setConfirmCallback(this);
            rabbitTemplate.setReturnCallback(this);
            CorrelationData correlationData1=new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend("LogisticsStat","LogisticsStat",payPrama.orderId,new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setHeader("x-delay", 30000);
                    return message;
                }
            });

        }catch (Exception e)
        {
            //创建物流失败，重试
            e.printStackTrace();
            channel.basicReject(deleverTag,true);
        }

    }

    @RabbitListener(queues = "LogisticsStat")
    public void changestate(@Payload String  orderid, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deleverTag) throws IOException
    {
            log.info(orderid);
            if(orderid==null)
            {channel.basicReject(deleverTag,false);return;}
            logistics Log=getLogistics(orderid);
            if(Log==null)
            {
                channel.basicReject(deleverTag,false);
                return;
            }
            Log.setState(true);
            logisticsService.NewLogistics(Log);
            channel.basicAck(deleverTag,false);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }

    @Override
    public void returnedMessage(org.springframework.amqp.core.Message message, int i, String s, String s1, String s2) {

    }
}
