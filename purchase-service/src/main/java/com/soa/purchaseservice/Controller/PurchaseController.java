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

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    HttpServletRequest request;

    //购买商品
    @PostMapping("/v1/Purchase")
    public Stand_Result Purchase(@RequestBody PurchaseParam purchaseParam)
    {
        String id=UUID.randomUUID().toString();//生成订单id
        purchaseParam.Order_id=id;
        purchaseParam.Num=1;//设置购买量为1
        //改为从token取id
        purchaseParam.User_id=request.getHeader("uid");
        //检查库存
        try {
        Book book= inventoryRemote.QueryInventory(purchaseParam.Book_id);
        if(book.getInventory_quantity()<1)
        {
            Stand_Result result=new Stand_Result();
            result.setErrorMessage("库存不足");
            result.setSucceed(false);
            result.setWrongCode("107");
            Date date=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            result.setTime(df.format(date));
            return result;
        }
        }catch (Exception e)
        {
            e.printStackTrace();
            //库存服务挂掉了
            e.printStackTrace();
            Stand_Result result=new Stand_Result();
            result.setErrorMessage("库存服务错误");
            result.setSucceed(false);
            result.setWrongCode("106");
            Date date=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            result.setTime(df.format(date));
            return result;
        }
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
            Stand_Result result=new Stand_Result();
            result.setErrorMessage("消息队列错误");
            result.setSucceed(false);
            result.setWrongCode("103");
            Date date=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            result.setTime(df.format(date));
            return result;
        }

        Stand_Result result= new Stand_Result();
        result.setSucceed(true);
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        result.setTime(df.format(date));
        result.setMessage(id);

        return result;
    }

    //取消订单
    @DeleteMapping("/v1/Purchase/{id}")
    public Stand_Result CanclePurchase(@PathVariable("id") String id)
    {
        PurchaseParam purchaseParam = new PurchaseParam();
        //交给库存服务
        try {

            form order = orderRemote.GetOrder(id);
            log.info(id);
            log.info(order.getBookId());
            purchaseParam.Book_id = order.getBookId();
            purchaseParam.User_id = order.getCustomerId();
            purchaseParam.price = order.getSinglePrice();
            purchaseParam.Num = 0 - order.getBookNum();
            purchaseParam.Order_id = id;
        }catch (Exception e)
        {
            e.printStackTrace();
            Stand_Result result=new Stand_Result();
            result.setErrorMessage("Order微服务错误");
            result.setSucceed(false);
            result.setWrongCode("104");
            Date date=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            result.setTime(df.format(date));
            return result;
        }
            try{
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setMandatory(true);
            rabbitTemplate.setConfirmCallback(this);
            rabbitTemplate.setReturnCallback(this);
            CorrelationData correlationData1=new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend("Inventory","UpdateInventory",purchaseParam,correlationData1);
        }catch (Exception e)
        {
            e.printStackTrace();
            Stand_Result result=new Stand_Result();
            result.setErrorMessage("消息队列错误");
            result.setSucceed(false);
            result.setWrongCode("103");
            Date date=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            result.setTime(df.format(date));
            return result;
        }
        Stand_Result result= new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        result.setTime(df.format(date));

        return result;
    }

    //支付订单
    @PostMapping("/v1/Pay/")
    public Stand_Result Pay(@RequestBody PayPrama payPrama) throws InterruptedException {

        //检查余额够不够
        //从token取id，用id，Money服务去查
        //从token里取id
        payPrama.userId=request.getHeader("uid");
        try {
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setMandatory(true);
            rabbitTemplate.setConfirmCallback(this);
            rabbitTemplate.setReturnCallback(this);
            CorrelationData correlationData1=new CorrelationData(UUID.randomUUID().toString());

            payPrama.totalPrice=0;
            rabbitTemplate.convertAndSend("Order","UpdateOrder",payPrama,correlationData1);
        }catch (Exception e)
        {
            e.printStackTrace();
            Stand_Result result=new Stand_Result();
            result.setErrorMessage("消息队列错误");
            result.setSucceed(false);
            result.setWrongCode("103");
            Date date=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            result.setTime(df.format(date));
            return result;
        }

        Stand_Result result= new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        result.setTime(df.format(date));

        return result;
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
