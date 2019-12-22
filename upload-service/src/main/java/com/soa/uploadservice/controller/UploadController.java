package com.soa.uploadservice.controller;

import com.soa.uploadservice.pojo.Book;
import com.soa.uploadservice.pojo.BookInfo;
import com.soa.uploadservice.pojo.Stand_Result;
import com.soa.uploadservice.pojo.UploadParam;
import com.soa.uploadservice.remote.BookRemote;
import com.soa.uploadservice.remote.InventoryRemote;
import com.spring4all.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.security.auth.message.callback.SecretKeyCallback;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@EnableSwagger2Doc
@RestController
@Slf4j
@CrossOrigin(maxAge = 3600,origins = "*")
public class UploadController implements RabbitTemplate.ReturnCallback,RabbitTemplate.ConfirmCallback {

    @Autowired
    HttpServletRequest request;


    @Autowired
    BookRemote bookRemote;
    @Autowired
    InventoryRemote inventoryRemote;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @PostMapping("/v1/Upload")
    public Stand_Result uploadbook(@RequestBody UploadParam uploadParam)
    {

        Book book=new Book();
        BookInfo bookInfo=new BookInfo();
        //生成随机id
        String id=UUID.randomUUID().toString();
        book.setBook_id(id);
        //book.setUser_id(uploadParam.getUser_id());
        //改为从token获取id
        book.setUser_id(request.getHeader("uid"));
        book.setBook_price(uploadParam.getBook_price());
        book.setInventory_quantity(1);

        bookInfo.setId(id);
        bookInfo.setBook_id(book.getBook_id());
        bookInfo.setBook_title(uploadParam.getBook_title());
        bookInfo.setBook_type(uploadParam.getBook_type());
        bookInfo.setPicture_url(uploadParam.getPicture_url());
        bookInfo.setPublisher(uploadParam.getPublisher());
        //bookInfo.setAuthor(uploadParam.getAuthor());
        bookInfo.setDescribe(uploadParam.getDescribe());
        bookInfo.setBook_price(uploadParam.getString_price());
        //测试消息队列
        try {
            log.info("发送消息");
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setMandatory(true);
            rabbitTemplate.setConfirmCallback(this);
            rabbitTemplate.setReturnCallback(this);
            CorrelationData correlationData1=new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend("Inventory","NewInventory",book,correlationData1);

            CorrelationData correlationData2=new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend("Book", "NewBook", bookInfo,  correlationData2);

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

//        //将参数拆分成两个部分
//        Book book=new Book();
//        BookInfo bookInfo=new BookInfo();
//        book.setUser_id(uploadParam.getUser_id());
//        book.setBook_price(uploadParam.getBook_price());
//        book.setInventory_quantity(uploadParam.getInventory_quantity());
//        String  id=inventoryRemote.NewInventory(book);
//
//
//        bookInfo.setId(id);
//        bookInfo.setBook_id(book.getBook_id());
//        bookInfo.setBook_title(uploadParam.getBook_title());
//        bookInfo.setBook_type(uploadParam.getBook_type());
//        bookInfo.setPicture_url(uploadParam.getPicture_url());
//        bookInfo.setPublisher(uploadParam.getPublisher());
//        bookInfo.setAuthor(uploadParam.getAuthor());
//        bookInfo.setDescribe(uploadParam.getDescribe());
//        bookInfo.setBook_price(uploadParam.getString_price());
//
//        //调用remote，然后得出结果
//         bookRemote.NewBook(bookInfo);
//
//
        Stand_Result result=new Stand_Result();
        result.setWrongCode("0");
        result.setSucceed(true);
        return result;
    }

    @PutMapping("/v1/Upload/Inventory")
    public Stand_Result UpdateInventory(@RequestBody Book book)
    {
        inventoryRemote.Update(book);
        Stand_Result result=new Stand_Result();
        result.setWrongCode("0");
        result.setSucceed(true);
        return result;
    }

    @PutMapping("/v1/Upload/BookInfo")
    public Stand_Result UpdateBook(@RequestBody BookInfo bookInfo)
    {
        bookRemote.Update(bookInfo);
        Stand_Result result=new Stand_Result();
        result.setWrongCode("0");
        result.setSucceed(true);
        return result;
    }

    @DeleteMapping("/v1/Upload/{id}")
    public Stand_Result DeleteBook(@PathVariable("id") String id)
    {
        bookRemote.DeleteBook(id);
        inventoryRemote.DeleteInventory(id);

        Stand_Result result=new Stand_Result();
        result.setWrongCode("0");
        result.setSucceed(true);
        return result;
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }
    @Override
    public void confirm(CorrelationData correlationData,boolean b,String s)
    {
        System.out.println("confirm: " + correlationData.getId() + ",s=" + s + ",b:" + b);
    }
}
