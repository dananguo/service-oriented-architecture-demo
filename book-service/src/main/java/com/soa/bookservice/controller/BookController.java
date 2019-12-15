package com.soa.bookservice.controller;

import com.rabbitmq.client.Channel;
import com.soa.bookservice.pojo.BookInfo;
import com.soa.bookservice.pojo.Stand_Result;
import com.soa.bookservice.service.BookService;
import com.spring4all.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@EnableSwagger2Doc
@RestController
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
//    //新建书籍信息
//    @PostMapping("/v1/Book")
//    public String  NewBook(@RequestBody BookInfo bookInfo) {
//        bookService.save(bookInfo);
//        Stand_Result result=new Stand_Result();
//        return bookInfo.getId();
//    }
@RabbitListener(queues = "NewBook")
public void NewBook(@Payload BookInfo bookInfo, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deleverTag) throws IOException {
    log.info("收到的对象：  "+bookInfo.getBook_title());
    bookService.save(bookInfo);
    //手动签收
    channel.basicAck(deleverTag,false);
}

    //修改书籍信息
    @PutMapping("/v1/Book")
    public Stand_Result Update(@RequestBody BookInfo bookInfo) {
        bookService.deleteById(bookInfo.getId());
        bookService.save(bookInfo);

        Stand_Result result=new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        /*未加入操作时间*/
        return result;
    }

    //删除书籍信息
    @DeleteMapping("/v1/Book/{id}")
    public Stand_Result DeleteBook(@PathVariable("id") String id) {

        bookService.deleteById(id);
        Stand_Result result=new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        /*未加入操作时间*/
        return result;
    }


    //查找书籍信息
    @ResponseBody
    @GetMapping("/v1/Book/{id}")
    public BookInfo QueryBook(@PathVariable("id") String id){

        BookInfo bookInfo=bookService.findById(id);
        return bookInfo;
    }

    //通过书名查找书籍信息
    @ResponseBody
    @GetMapping("/v1/book-by-title/{title}")
    public List<BookInfo> QueryBookByTitle(@PathVariable(value="title") String title){

        return bookService.findByBookName(title);

    }

    //查找部分书籍
    @GetMapping("/v1/Books/{count}")
    public List QuerySomeBook(@PathVariable("count") int count){
        int index=0;
        List books=new ArrayList();
        List bookList=bookService.findAll();
        Random random=new Random();
        while(index<count&&index<bookList.size()){
            books.add(bookList.get(random.nextInt(bookList.size())));
            index++;


        }
        return books;
    }


}

