package com.soa.bookservice.controller;

import com.soa.bookservice.pojo.BookInfo;
import com.soa.bookservice.pojo.Stand_Result;
import com.soa.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    //新建书籍信息
    @PostMapping("/Book")
    public String  NewBook(@RequestBody BookInfo bookInfo) {
        bookService.save(bookInfo);
        Stand_Result result=new Stand_Result();
        return bookInfo.getId();
    }

    //修改书籍信息
    @PutMapping("/Book")
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
    @DeleteMapping("/Book")
    public Stand_Result DeleteBook(@RequestParam(value = "id") String id) {

        bookService.deleteById(id);
        Stand_Result result=new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        /*未加入操作时间*/
        return result;
    }
}
