package com.soa.moneyservice.Controller;

import com.rabbitmq.client.Channel;
import com.soa.moneyservice.pojo.Money;
import com.soa.moneyservice.pojo.PayPrama;
import com.soa.moneyservice.pojo.Sign_up_params;
import com.soa.moneyservice.service.MoneyService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@EnableSwagger2Doc
@RestController
@Slf4j
public class MoneyController implements RabbitTemplate.ReturnCallback,RabbitTemplate.ConfirmCallback{
    @Autowired
    MoneyService moneyService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "NewMoney")
    public void NewMoney(@Payload Sign_up_params sign_up_params, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deleverTag) throws IOException
    {
        try {
            Money money=new Money();
            money.setId(sign_up_params.id);
            money.setMoney(2000);
            log.info(money.getId());
            moneyService.UpdateMoney(money);
            channel.basicAck(deleverTag,false);
        }catch (Exception e)
        {
            //因为账户已经建立，需重试
            e.printStackTrace();
            channel.basicReject(deleverTag,true);
        }
    }
    @GetMapping("/v1/Money/{userid}")
    public Money GetMoney(@PathVariable("userid") String id)
    {
        log.info(id);
        try{
            Money money=moneyService.FindMoneyById(id);

            return money;
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return new Money();
    }

    @RabbitListener(queues = "UpdateMoney")
    public void UpdateMoney(@Payload PayPrama payPrama, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deleverTag) throws IOException
    {
        try {
            Money money=moneyService.FindMoneyById(payPrama.userId);
            int nowmoney=money.getMoney();
            int newmoney=nowmoney-payPrama.totalPrice;
            if(newmoney>=0)
            {
                //余额充足，扣费
                money.setMoney(newmoney);
                moneyService.UpdateMoney(money);
                //扣费后，创建订单改变和物流任务
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setMandatory(true);
                rabbitTemplate.setConfirmCallback(this);
                rabbitTemplate.setReturnCallback(this);
                CorrelationData correlationData1=new CorrelationData(UUID.randomUUID().toString());
                rabbitTemplate.convertAndSend("Order","UpdateOrder",payPrama,correlationData1);
                CorrelationData correlationData2=new CorrelationData(UUID.randomUUID().toString());
                rabbitTemplate.convertAndSend("Logistics","NewLogistics",payPrama,correlationData2);
            }
            else {
                //余额不足，拒绝消息,不重试
                channel.basicReject(deleverTag,false);
            }
            money.setMoney(newmoney);
            moneyService.UpdateMoney(money);
            //写入成功，确认消费
            channel.basicAck(deleverTag,false);
        }catch (Exception e)
        {
            //因程序原因，拒绝消息，重试
            e.printStackTrace();
            log.info("余额更新操作失败，重试");
            channel.basicReject(deleverTag,true);
        }
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }
}
