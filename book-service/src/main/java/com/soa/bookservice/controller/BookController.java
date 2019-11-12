package com.soa.bookservice.controller;

import com.soa.bookservice.pojo.BookInfo;
import com.soa.bookservice.pojo.Stand_Result;
import com.soa.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    //新建书籍信息
    @PostMapping("/Book")
    public Stand_Result NewBook(@RequestBody BookInfo bookInfo) {
        bookService.save(bookInfo);
        Stand_Result result=new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        /*未加入操作时间*/
        return result;
    }

    //修改书籍信息(暂时只做了标题修改)
    @PutMapping("/Book")
    public Stand_Result ModifyBook(@RequestBody BookInfo bookInfo) {
        bookService.updateTitleById(bookInfo.getBook_id(),bookInfo.getBook_title());
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
    @GetMapping("Book")
    public BookInfo QueryBook(@RequestParam(value="id") String id){

        return bookService.findById(id);
    }

    //查找部分书籍
    @GetMapping("Books")
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
