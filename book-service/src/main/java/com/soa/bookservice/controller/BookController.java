package com.soa.bookservice.controller;

import com.soa.bookservice.pojo.BookInfo;
import com.soa.bookservice.pojo.Stand_Result;
import com.soa.bookservice.service.BookService;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@EnableSwagger2Doc
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    //新建书籍信息
    @PostMapping("/Book")
    public String  NewBook(@RequestBody BookInfo bookInfo) {
        bookService.save(bookInfo);
        Stand_Result result=new Stand_Result();
        return bookInfo.getBook_id();
    }

    //修改书籍信息
    @PutMapping("/Book")
    public Stand_Result Update(@RequestBody BookInfo bookInfo) {
        bookService.deleteById(bookInfo.getBook_id());
        bookService.save(bookInfo);

        Stand_Result result=new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        /*未加入操作时间*/
        return result;
    }

    //删除书籍信息
    @DeleteMapping("/Book")
    public Stand_Result DeleteBook(@RequestParam(value = "id") String id) {

        bookService.deleteById(id);
        Stand_Result result=new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        /*未加入操作时间*/
        return result;
    }


    //查找书籍信息
    @ResponseBody
    @GetMapping("/Book")
    public BookInfo QueryBook(@RequestParam(value="id") String id){

        BookInfo bookInfo=bookService.findById(id);
        return bookInfo;
    }

    //查找部分书籍
    @GetMapping("/Books")
    public List QuerySomeBook(@RequestParam(value="count") int count){
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

